# 数据库迁移策略与用户数据保护政策

## 核心原则

**【最高优先级】版本升级时必须保留用户数据，确保数据向后兼容**

## 设计原则

### 1. 用户数据保护原则
- ✅ **必须**：版本升级时保留所有用户数据
- ❌ **禁止**：使用 `fallbackToDestructiveMigration()`
- ✅ **必须**：为每个数据库版本变更提供明确的迁移路径
- ✅ **必须**：在发布前测试所有迁移路径

### 2. 数据库版本管理规则

#### 2.1 版本号管理
- 每次数据库结构变更必须增加版本号
- 版本号必须连续递增
- 在 `@Database` 注解中更新 `version` 参数

#### 2.2 迁移实现要求
- 必须为每个版本升级提供 `Migration` 对象
- Migration 命名格式：`MIGRATION_X_Y`（从版本X到版本Y）
- 必须在 `Room.databaseBuilder()` 中通过 `.addMigrations()` 注册所有迁移

#### 2.3 SQL 编写规范
- Migration 中只能使用 SQL 语句，不能使用 Entity 类
- 新增字段必须设置默认值或允许 NULL
- 删除字段时要考虑旧版本应用的兼容性
- 重命名字段时使用 ALTER TABLE 而不是删除+新增

### 3. 字段变更规范

#### 3.1 新增字段
```kotlin
// ✅ 正确：设置默认值或允许NULL
database.execSQL("ALTER TABLE card ADD COLUMN daily_rate REAL DEFAULT NULL")
database.execSQL("ALTER TABLE card ADD COLUMN is_active INTEGER NOT NULL DEFAULT 1")
```

```kotlin
// ❌ 错误：新增NOT NULL字段且无默认值
database.execSQL("ALTER TABLE card ADD COLUMN daily_rate REAL NOT NULL")
```

#### 3.2 删除字段
```kotlin
// ✅ 正确：创建新表，复制数据，删除旧表
database.execSQL("""
    CREATE TABLE card_new (
        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
        name TEXT NOT NULL,
        type TEXT NOT NULL
    )
""")
database.execSQL("INSERT INTO card_new SELECT id, name, type FROM card")
database.execSQL("DROP TABLE card")
database.execSQL("ALTER TABLE card_new RENAME TO card")
```

#### 3.3 修改字段类型
```kotlin
// ✅ 正确：创建新表，转换数据，替换旧表
database.execSQL("""
    CREATE TABLE card_new (
        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
        amount TEXT NOT NULL  -- 从 REAL 改为 TEXT
    )
""")
database.execSQL("INSERT INTO card_new SELECT id, CAST(amount AS TEXT) FROM card")
database.execSQL("DROP TABLE card")
database.execSQL("ALTER TABLE card_new RENAME TO card")
```

### 4. 迁移测试要求

#### 4.1 测试场景
- [ ] 从每个旧版本升级到最新版本
- [ ] 跨多个版本升级（如从版本1直接升级到版本5）
- [ ] 包含数据的数据库升级
- [ ] 空数据库升级

#### 4.2 测试内容
- [ ] 数据完整性：所有数据正确保留
- [ ] 数据准确性：数据值未被修改
- [ ] 功能正常：升级后应用功能正常
- [ ] 性能测试：大数据量迁移性能

## 实施示例

### 当前项目迁移记录

#### Version 1 → Version 2
**变更时间**: 2026-01-25
**变更内容**: 为Card表添加 `daily_rate` 字段（天数卡每天收费金额）
**迁移代码**:
```kotlin
private val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE card ADD COLUMN daily_rate REAL DEFAULT NULL"
        )
    }
}
```
**影响范围**:
- 新增字段，不影响现有数据
- 旧版本天数卡的 `daily_rate` 为 NULL
- 应用层需要处理 NULL 值情况

**测试结果**: ✅ 通过
- [x] 现有卡片数据完整保留
- [x] 新建天数卡可以设置每天收费
- [x] 旧天数卡显示正常

---

## 开发流程

### 修改数据库结构时的步骤

1. **修改 Entity 类**
   - 更新数据类定义
   - 添加/修改/删除字段

2. **增加数据库版本号**
   ```kotlin
   @Database(
       entities = [Card::class, Transaction::class],
       version = 3,  // 从 2 增加到 3
       exportSchema = false
   )
   ```

3. **创建 Migration**
   ```kotlin
   private val MIGRATION_2_3 = object : Migration(2, 3) {
       override fun migrate(database: SupportSQLiteDatabase) {
           // 编写迁移SQL
       }
   }
   ```

4. **注册 Migration**
   ```kotlin
   .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
   ```

5. **更新本文档**
   - 记录变更内容
   - 记录迁移代码
   - 记录测试结果

6. **测试迁移**
   - 安装旧版本应用
   - 创建测试数据
   - 升级到新版本
   - 验证数据完整性

## 紧急情况处理

### 如果发现迁移错误

1. **立即停止发布**
2. **评估影响范围**
   - 有多少用户受影响？
   - 数据丢失程度？
3. **修复方案**
   - 修正 Migration 代码
   - 增加版本号
   - 提供数据恢复方案
4. **发布热修复版本**

### 数据恢复方案

如果用户数据已经损坏：
1. 提供备份恢复功能
2. 从服务器恢复（如果有云同步）
3. 提供数据导入工具

## 代码审查检查清单

在代码审查时，必须检查以下项目：

- [ ] 是否修改了数据库版本号？
- [ ] 是否提供了 Migration？
- [ ] Migration 是否已注册？
- [ ] 是否移除了 `fallbackToDestructiveMigration()`？
- [ ] 新增字段是否有默认值或允许 NULL？
- [ ] 是否更新了本文档？
- [ ] 是否进行了迁移测试？

## 参考资料

- [Room 数据库迁移官方文档](https://developer.android.com/training/data-storage/room/migrating-db-versions)
- [SQLite ALTER TABLE 文档](https://www.sqlite.org/lang_altertable.html)

## 版本历史

| 版本 | 日期 | 变更内容 | 负责人 |
|------|------|----------|--------|
| 1 | 2026-01-25 | 初始版本 | Claude |
| 2 | 2026-01-25 | 添加 daily_rate 字段 | Claude |

---

**最后更新**: 2026-01-25
**文档状态**: 生效中
**强制执行**: 是

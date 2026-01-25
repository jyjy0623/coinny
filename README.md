# 储值卡记账 Android App

一个功能完整的Android储值卡管理应用，支持多种卡片类型的创建、充值、扣费和统计功能。

## 功能特性

### 核心功能
- **三种卡片类型**
  - 金额卡：按使用金额扣费
  - 次数卡：按使用次数扣费
  - 天数卡：按天扣费，支持暂停/启用

- **卡片管理**
  - 创建/编辑/删除卡片
  - 设置过期时间
  - 充值功能
  - 扣费功能
  - 暂停/启用（仅天数卡）

- **交易记录**
  - 完整的交易历史记录
  - 支持创建、充值、扣费三种交易类型
  - 可添加备注信息

- **通知提醒**
  - 卡片即将过期提醒（7天内）
  - 余额不足提醒（低于初始值10%）
  - 后台定期检查（每天一次）

- **数据备份**
  - 导出为JSON格式
  - 导出为CSV格式
  - 支持数据恢复

## 技术栈

- **语言**: Kotlin
- **UI**: XML Views + ViewBinding
- **架构**: MVVM + Repository Pattern
- **数据库**: Room (SQLite)
- **异步处理**: Coroutines + Flow
- **后台任务**: WorkManager
- **最低SDK**: 24 (Android 7.0)
- **目标SDK**: 34 (Android 14)

## 项目结构

```
app/src/main/java/com/coinny/storedcard/
├── data/
│   ├── local/
│   │   ├── dao/              # 数据访问对象
│   │   ├── entity/           # 数据库实体
│   │   ├── AppDatabase.kt    # 数据库配置
│   │   └── Converters.kt     # 类型转换器
│   └── repository/           # 数据仓库
├── domain/
│   ├── model/                # 领域模型（枚举）
│   └── usecase/              # 业务用例
├── ui/
│   ├── main/                 # 主界面
│   ├── addedit/              # 添加/编辑卡片
│   ├── carddetail/           # 卡片详情
│   └── deduct/               # 扣费/充值对话框
├── util/                     # 工具类
├── worker/                   # 后台任务
└── StoredCardApplication.kt  # Application类
```

## 数据库设计

### Card表
- id: 主键
- name: 卡片名称
- type: 卡片类型（AMOUNT/COUNT/DAILY）
- initial_value: 初始值
- current_value: 当前值
- expiry_date: 过期时间
- status: 状态（ACTIVE/PAUSED/EXPIRED）
- created_at: 创建时间
- updated_at: 更新时间
- last_deduct_date: 上次扣费日期

### Transaction表
- id: 主键
- card_id: 关联卡片ID
- type: 交易类型（CREATE/RECHARGE/DEDUCT）
- amount: 金额/次数/天数
- note: 备注
- timestamp: 时间戳

## 构建和运行

1. 克隆项目到本地
2. 使用Android Studio打开项目
3. 等待Gradle同步完成
4. 连接Android设备或启动模拟器
5. 点击运行按钮

## 权限说明

- `POST_NOTIFICATIONS`: 用于发送过期和余额提醒通知
- `READ_EXTERNAL_STORAGE`: 用于读取备份文件（Android 12及以下）
- `WRITE_EXTERNAL_STORAGE`: 用于写入备份文件（Android 10及以下）

## 使用说明

### 创建卡片
1. 点击主界面右下角的"+"按钮
2. 输入卡片名称
3. 选择卡片类型（金额卡/次数卡/天数卡）
4. 输入初始值
5. 可选：设置过期时间
6. 点击保存

### 充值
1. 进入卡片详情页
2. 点击"充值"按钮
3. 输入充值金额/次数/天数
4. 可选：添加备注
5. 点击确认

### 扣费
1. 进入卡片详情页
2. 点击"扣费"按钮
3. 输入扣费金额/次数
4. 可选：添加备注
5. 点击确认

### 暂停/启用（仅天数卡）
1. 进入天数卡详情页
2. 点击"暂停"或"启用"按钮
3. 暂停期间不会计算天数消耗

### 备份数据
1. 在主界面点击右上角菜单
2. 选择"备份导出"
3. 数据将保存到应用外部存储目录

## 开发计划

- [ ] 统计报表功能（图表展示）
- [ ] 数据导入功能
- [ ] 卡片分类功能
- [ ] 多语言支持
- [ ] 深色模式
- [ ] 桌面小部件

## 许可证

MIT License

## 作者

Created with Claude Code

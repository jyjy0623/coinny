# APP版本检查服务搭建指南

## 概述

本文档介绍如何搭建APP版本检查服务，让应用能够检测到新版本并提示用户升级。

## 方案对比

| 方案 | 难度 | 成本 | 适用场景 |
|------|------|------|----------|
| 静态JSON文件 | ⭐ | 免费 | 个人项目、小型应用 |
| GitHub Releases | ⭐⭐ | 免费 | 开源项目 |
| 简单后端服务 | ⭐⭐⭐ | 低成本 | 中小型应用 |
| 完整后端系统 | ⭐⭐⭐⭐⭐ | 较高 | 企业级应用 |

---

## 方案一：静态JSON文件（推荐入门）

### 优点
- ✅ 最简单，无需编程
- ✅ 完全免费
- ✅ 维护方便
- ✅ 可靠性高

### 缺点
- ❌ 无法统计更新数据
- ❌ 无法针对不同用户推送不同版本

### 实施步骤

#### 1. 准备version.json文件

项目中已经创建了 `version.json` 文件，内容如下：

\`\`\`json
{
  "versionCode": 2,
  "versionName": "1.1.0",
  "downloadUrl": "https://your-domain.com/downloads/coinny-v1.1.0.apk",
  "updateMessage": "更新内容：\\n1. 修改天数卡功能\\n2. 添加每天收费配置\\n...",
  "forceUpdate": false
}
\`\`\`

**字段说明**：
- `versionCode`: 版本号（整数），必须大于当前版本
- `versionName`: 版本名称（字符串），如"1.1.0"
- `downloadUrl`: APK下载地址
- `updateMessage`: 更新说明，支持\\n换行
- `forceUpdate`: 是否强制更新（true/false）

#### 2. 选择托管服务

##### 选项A：GitHub Pages（推荐）

**步骤**：
1. 在GitHub创建仓库（可以是私有仓库）
2. 将 `version.json` 上传到仓库
3. 启用GitHub Pages
4. 访问地址：`https://username.github.io/repo-name/version.json`

**优点**：
- 免费
- 稳定可靠
- 支持HTTPS
- 全球CDN加速

##### 选项B：Gitee Pages（国内推荐）

**步骤**：
1. 在Gitee创建仓库
2. 上传 `version.json`
3. 启用Gitee Pages
4. 访问地址：`https://username.gitee.io/repo-name/version.json`

**优点**：
- 国内访问速度快
- 免费
- 操作简单

##### 选项C：云存储服务

**阿里云OSS**：
1. 创建OSS Bucket（公共读）
2. 上传 `version.json`
3. 获取文件URL

**腾讯云COS**：
1. 创建COS存储桶（公有读私有写）
2. 上传 `version.json`
3. 获取访问URL

**成本**：每月几毛钱（流量很小）

##### 选项D：自己的服务器

如果你有自己的服务器或虚拟主机：
1. 将 `version.json` 上传到网站目录
2. 确保可以通过HTTP/HTTPS访问
3. 设置正确的CORS头（如果需要）

#### 3. 修改APP代码

修改 `VersionCheckUtil.kt` 中的URL：

\`\`\`kotlin
private const val VERSION_CHECK_URL = "https://your-actual-url.com/version.json"
\`\`\`

将URL替换为你实际的version.json地址。

#### 4. 托管APK文件

APK文件也需要托管，有以下选择：

**选项A：GitHub Releases**
1. 在GitHub仓库创建Release
2. 上传APK文件作为附件
3. 获取下载链接
4. 更新version.json中的downloadUrl

**选项B：云存储**
- 上传APK到阿里云OSS/腾讯云COS
- 获取公开访问链接
- 更新version.json

**选项C：自己的服务器**
- 上传APK到服务器
- 确保可以直接下载

#### 5. 测试流程

1. 确保version.json可以访问
2. 确保APK可以下载
3. 在APP中点击"检查更新"
4. 验证更新提示是否正常显示
5. 测试下载和安装流程

#### 6. 发布新版本流程

每次发布新版本时：

1. **编译新版本APK**
   - 修改 `build.gradle` 中的 `versionCode` 和 `versionName`
   - 编译生成APK

2. **上传APK**
   - 上传到托管服务
   - 获取下载链接

3. **更新version.json**
   \`\`\`json
   {
     "versionCode": 3,  // 增加版本号
     "versionName": "1.2.0",  // 更新版本名
     "downloadUrl": "新的APK下载地址",
     "updateMessage": "新版本更新内容",
     "forceUpdate": false
   }
   \`\`\`

4. **上传version.json**
   - 覆盖旧的version.json文件

5. **测试**
   - 用旧版本APP测试更新功能

---

## 方案二：GitHub Releases API

### 优点
- ✅ 完全免费
- ✅ 自动化程度高
- ✅ 版本管理方便
- ✅ 适合开源项目

### 实施步骤

#### 1. 在GitHub创建Release

每次发布新版本：
1. 进入GitHub仓库
2. 点击 "Releases" → "Create a new release"
3. 填写Tag（如v1.1.0）
4. 填写Release标题和说明
5. 上传APK文件
6. 发布Release

#### 2. 修改APP代码

需要修改 `VersionCheckUtil.kt` 使用GitHub API：

\`\`\`kotlin
private const val GITHUB_API_URL = "https://api.github.com/repos/username/repo/releases/latest"
\`\`\`

解析GitHub API返回的JSON获取版本信息。

### 缺点
- 国内访问GitHub可能较慢
- 需要处理API限流

---

## 方案三：简单后端服务

如果你想要更多控制和统计功能，可以搭建简单的后端服务。

### 技术选择

#### 选项A：Node.js + Express（最简单）

#### 选项B：Python + Flask

#### 选项C：PHP（虚拟主机友好）

### 基本功能

1. **版本检查接口**
   - GET /api/version
   - 返回最新版本信息

2. **统计功能**（可选）
   - 记录检查更新次数
   - 记录下载次数
   - 用户版本分布

3. **管理后台**（可选）
   - 发布新版本
   - 查看统计数据
   - 灰度发布控制

---

## 推荐方案总结

### 个人开发者/小型项目
**推荐：方案一（静态JSON + GitHub Pages/Gitee Pages）**
- 零成本
- 零维护
- 足够可靠

### 开源项目
**推荐：方案二（GitHub Releases）**
- 与代码仓库集成
- 版本管理方便
- 社区友好

### 商业项目
**推荐：方案三（后端服务）**
- 完整的数据统计
- 灵活的版本控制
- 支持灰度发布

---

## 下一步

选择适合你的方案后，按照步骤实施即可。如果选择方案一（静态JSON），你现在就可以开始：

1. 选择一个托管服务（推荐GitHub Pages或Gitee Pages）
2. 上传 `version.json` 文件
3. 修改APP中的 `VERSION_CHECK_URL`
4. 测试更新功能

需要帮助？请参考下一节的详细教程。

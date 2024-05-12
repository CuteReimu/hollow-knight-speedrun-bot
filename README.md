# 空洞骑士speedrun推送小助手

![](https://img.shields.io/github/languages/top/CuteReimu/hollow-knight-speedrun-bot "语言")
[![](https://img.shields.io/github/actions/workflow/status/CuteReimu/hollow-knight-speedrun-bot/build.yml?branch=master)](https://github.com/CuteReimu/hollow-knight-speedrun-bot/actions/workflows/build.yml "代码分析")
[![](https://img.shields.io/github/contributors/CuteReimu/hollow-knight-speedrun-bot)](https://github.com/CuteReimu/hollow-knight-speedrun-bot/graphs/contributors "贡献者")
[![](https://img.shields.io/github/license/CuteReimu/hollow-knight-speedrun-bot)](https://github.com/CuteReimu/hollow-knight-speedrun-bot/blob/master/LICENSE "许可协议")
</div>

**本项目已迁移至 https://github.com/CuteReimu/YinYangJade**

## 编译

```shell
./gradlew buildPlugin
```

在`build/mirai`下可以找到编译好的jar包，即为Mirai插件

## 使用方法

1. 首先了解、安装并启动 [Mirai - Console Terminal](https://github.com/mamoe/mirai/blob/dev/docs/ConsoleTerminal.md) 。
   如有必要，你可能需要修改 `config/Console` 下的 Mirai 相关配置。
   **QQ登录、收发消息相关全部使用 Mirai 框架，若一步出现了问题，请去Mirai的repo或者社区寻找解决方案。**
2. 启动Mirai后，会发现生成了很多文件夹。将编译得到的插件jar包放入 `plugins` 文件夹后，重启Mirai。

## 配置文件：

第一次运行会自动生成配置文件`config/net.cutereimu.hkbot/HKConfig.yml`，如下：

```yaml
# 是否启用插件
enable: true
# speedrun推送间隔
speedrun_push_delay: 300
# speedrun推送QQ群
speedrun_push_qq_group:
  - 12345678
# speedrun的API Key
speedrun_api_key: xxxxxxxx
```

修改配置文件后重新启动即可

## 开发相关

如果你想要本地调试，执行如下命令即可：

```shell
./gradlew runConsole
```

上述命令会自动下载Mirai Console并运行，即可本地调试。本地调试时会生成一个`debug-sandbox`文件夹，和Mirai Console的文件夹结构基本相同，

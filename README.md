# handleplugin

An intellij plugin for running Microsoft's [handle](https://technet.microsoft.com/en-us/sysinternals/handle.aspx) directly from the IDE.
Handle specifies which process(es) that lock a certain file or folder.

## How to use
1) Build the plugin from source using intellij
2) Install plugin from jar
3) Go to File > Settings > Other settings > Handle plugin and specify the path to the handle executable
4) Right click on a file/folder in your project structure to run handle with the file/folder as argument
5) The output from handle will be shown in the Run Tool window

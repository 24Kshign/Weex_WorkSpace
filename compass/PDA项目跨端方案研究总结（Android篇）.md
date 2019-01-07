## PDA项目跨端方案研究总结（Android篇）

### 一、项目搭建

此次跨端方案使用`weex`技术来实现，使用`Vue.js`作为`weex`的前端框架

**1、创建项目**

在环境都搭好的前提下，在命令行执行 **`weex create projectName`** 命令，这样就创建了一个`weex`项目，以下就是一个weex项目的目录结构

![](http://ooaap25kv.bkt.clouddn.com/18-10-16/44755705.jpg)

**2、安装依赖的第三方js包**

进入项目的根目录，然后在命令行之行 **`npm install`** 命令，这样就会将第三方的`js`包都下载到本地。

**3、启动项目**

启动项目分为三种，`Web`、`Android`、`iOS`，启动Android或iOS时，需要事先执行 **`weex platform add android`** 或者 **`weex platform add ios`**，然后执行命令：

- **Web：执行命令 `weex run web`**

- **Android：执行命令 `weex run android`**

- **iOS：执行命令 `weex run ios`**

### 二、Vue相关

为了开发weex，首先需要会一点`Vue.js`的相关技术，同时还有一些`js`基础的相关知识，这里我在学习过程中总结了一些学习资料，也是边学习边记笔记：

[Android开发人员不得不学习的JavaScript基础（一）](https://www.jianshu.com/p/4a1c75990f84)

[Android开发人员不得不学习的JavaScript基础（二）](https://www.jianshu.com/p/b33068c297be)

[Android开发人员不得不学习的Vue.js基础](https://www.jianshu.com/p/1d8e33406fe9)

### 三、Android平台扩展

weex不仅仅只是提供一些功能以及机制，它还提供了一些可供H5，Android
，iOS扩展功能的机制

**1、Module扩展**

使用这个可以轻松完成`weex`与原生的交互，比如`weex`调用原生相机/相册功能，只需要在原生新建一个类继承`WXModule`，然后暴露方法给`js`调用就行了，需要注意的是，方法必须带上`@JSMethod`注解，来看看一个完整的过程：

```
Weex-Android端：

public class ImagePickerModule extends WXModule {

	@JSMethod
    public void selectPhoto(String photoNum, JSCallback callback) {
        //选择相册图片
        原生实现相应逻辑，然后通过JsCallBack将结果返回给weex前端
        callback.invoke();   //执行invoke方法，将前端需要的结果返回去
    }
    
    @JSMethod
    public void takePhoto(JSCallback callback) {
    
    }
}
然后需要在Application中注册这个类：
public class WXApplication extends Application {
    @Override
    public void onCreate() {
    	super.onCreate();
    	try {
    		WXSDKEngine.registerModule("imagePicker", ImagePickerModule.class);
    	} catch (WXException e) {
    		e.printStackTrace();
    	}
    }
}


Weex-H5端：

<script>
//这样就算把刚才原生端的类引进来了
const imagePicker = weex.requireModule('imagePicker')

//调用方法
imagePicker.selectPhoto('1', (handler) => {
	//handler为原生传过来的数据，可以用JSON格式化一下
})
</script>
```

**2、Adapter扩展**

`Adapter`扩展在`Android`端通常表现为图片适配器和网络下载适配器，比如：

```
public class ImageAdapter implements IWXImgLoaderAdapter {
	
	@Override
	public void setImage(final String url, final ImageView view, WXImageQuality quality, final WXImageStrategy strategy) {
		//实现加载（下载）图片的方法
	}
}

然后需要在Application中注册这个类：
public class WXApplication extends Application {
    @Override
    public void onCreate() {
    	super.onCreate();
    	
    	WXSDKEngine.initialize(this, new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build());
    }
}
```

除此之外，还有一个`Component`的扩展，这个扩展是为了在前端中使用一些原生的组件，定义好之后同样要在`Application`中进行注册，才能在前端使用。

### 四、遇到的一些问题

**1、跳转**

可以说这是遇到过最大的一个问题了；在`Android`中，`navigator`的`push`方法跳转的 `Activity`界面是需要处理的，需要创建一个带有特殊 `<intent-filter>` 标签的 `Activity`。运行在`Android`端之后，`weex`会自动把`js`文件更新到`android`的 **`src/assets/dist`** 目录下，然后会在`WXPageActivity`页面中处理路径地址，但是很奇怪的是第一次打开的时候接收到的是一个json格式的路径，后面跳转都是一个以file开头的字符串，但是weex帮我们实现的代码中并没有做判断，这就导致了在Android端跳转的过程中，一直都是首页，判断到当不是json串的时候，需要做处理。

```
AndroidManifest中：

<intent-filter tools:ignore="AppLinkUrlError">
    <action android:name="android.intent.action.VIEW" />
    <action android:name="com.alibaba.weex.protocol.openurl" />
    
    <category android:name="android.intent.category.DEFAULT" />
    <category android:name="com.taobao.android.intent.category.WEEX" />
    
    <data android:scheme="http" />
    <data android:scheme="https" />
    <data android:scheme="wxpage" />
    <data android:scheme="file" />
</intent-filter>

Activity中：

if (uri.toString().startsWith("file") || uri.toString().startsWith("http") || uri.toString().startsWith("https")) {
	mUri = uri;
} else {
	处理json格式的路径
}
```

**2、样式**

在`weex`中，有很多标签用了是没有效果的，不和`html`代码一样，举些例子：

- 在使用`input`标签作为按钮时是不生效的，`weex`只是识别这个标签为输入框；`button`标签是不带效果的，只会显示文案。
- 布局只支持 盒子模型、flex布局、`relative`定位，其他一些`CSS`不太支持。
- `CSS`的 `margin`、`padding`、`border` 不支持缩写。要分开写才能生效。
- `image`必须设置宽高，否则不显示。也不能使用`img`来显示图片。
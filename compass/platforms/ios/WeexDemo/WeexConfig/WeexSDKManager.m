//
//  WeexSDKManager.m
//  WeexDemo
//
//  Created by yangshengtao on 16/11/14.
//  Copyright © 2016年 taobao. All rights reserved.
//

#import "WeexSDKManager.h"
#import "DemoDefine.h"
#import <WeexSDK/WeexSDK.h>
#import <ATSDK/ATManager.h>

#import "WXDemoViewController.h"
#import "WXImgLoaderDefaultImpl.h"

#import "WXImagePicker.h"

@implementation WeexSDKManager

+ (void)setup {
    NSURL *url = nil;
#if DEBUG
    //If you are debugging in device , please change the host to current IP of your computer.
    url = [NSURL URLWithString:BUNDLE_URL];
#else
    url = [NSURL URLWithString:BUNDLE_URL];
#endif
    NSString * entryURL = [[NSBundle mainBundle] objectForInfoDictionaryKey:@"WXEntryBundleURL"];
    if (entryURL) {
        if ([entryURL hasPrefix:@"http"]) {
            url = [NSURL URLWithString:entryURL];
        } else {
            url = [NSURL URLWithString:[NSString stringWithFormat:@"%@%@",[[NSBundle bundleForClass:self] resourceURL].absoluteString, entryURL]];
        }
    }
    
#ifdef UITEST
    url = [NSURL URLWithString:UITEST_HOME_URL];
#endif
    
    [self initWeexSDK];
    [self loadCustomContainWithScannerWithUrl:url];
}

+ (void)initWeexSDK {
    [WXAppConfiguration setAppGroup:@"AliApp"];
    [WXAppConfiguration setAppName:@"WeexDemo"];
    [WXAppConfiguration setAppVersion:@"1.8.3"];
    [WXAppConfiguration setExternalUserAgent:@"ExternalUA"];
    
    [WXSDKEngine initSDKEnvironment];
    
    [self setupExtra];
    [self setupDebug];
}

+ (void)setupExtra {
    /**
     weex里分为Handler、Module、Component
     Handler: 类似调用服务，通过声明(interface/protocol)约定方法，并去实现这个方法，handler大多数情况是被Module和Component调用的，一般是提供给Native使用，例如图片加载(imageLoaderHandler)
     Module: 封装native层的一些操作方法，提供给weex使用，例如网络请求(stream)
     Component: Native层UI组件封装，提供给weex使用
     **/
    
    // 注册Handler
    [WXSDKEngine registerHandler:[WXImgLoaderDefaultImpl new] withProtocol:@protocol(WXImgLoaderProtocol)];
    
    // 注册Module
    [WXSDKEngine registerModule:@"imagePicker" withClass:[WXImagePicker class]];
    
    // 注册Component
    //    [WXSDKEngine registerComponent:@"" withClass:[]];
}

+ (void)setupDebug {
#ifdef DEBUG
//    [WXAnalyzerCenter addWxAnalyzer:[DebugAnalyzer new]];
    [self atAddPlugin];
    [WXDebugTool setDebug:YES];
    [WXLog setLogLevel:WXLogLevelLog];
    
    #ifndef UITEST
        [[ATManager shareInstance] show];
    #endif
#else
    [WXDebugTool setDebug:NO];
    [WXLog setLogLevel:WXLogLevelError];
#endif
}

+ (void)atAddPlugin {
    [[ATManager shareInstance] addPluginWithId:@"weex" andName:@"weex" andIconName:@"../weex" andEntry:@"" andArgs:@[@""]];
    [[ATManager shareInstance] addSubPluginWithParentId:@"weex" andSubId:@"logger" andName:@"logger" andIconName:@"log" andEntry:@"WXATLoggerPlugin" andArgs:@[@""]];
    [[ATManager shareInstance] addSubPluginWithParentId:@"weex" andSubId:@"viewHierarchy" andName:@"hierarchy" andIconName:@"log" andEntry:@"WXATViewHierarchyPlugin" andArgs:@[@""]];
    [[ATManager shareInstance] addSubPluginWithParentId:@"weex" andSubId:@"scanner" andName:@"WXATScanner" andIconName:@"at_arr_refresh" andEntry:@"WXATScannerPlugin" andArgs:@[@""]];
//    [[ATManager shareInstance] addSubPluginWithParentId:@"weex" andSubId:@"test3" andName:@"test" andIconName:@"at_arr_refresh" andEntry:@"" andArgs:@[]];
}

+ (void)loadCustomContainWithScannerWithUrl:(NSURL *)url {
    UIViewController *demo = [[WXDemoViewController alloc] init];
    ((WXDemoViewController *)demo).url = url;
    [[UIApplication sharedApplication] delegate].window.rootViewController = [[WXRootViewController alloc] initWithRootViewController:demo];
}

@end

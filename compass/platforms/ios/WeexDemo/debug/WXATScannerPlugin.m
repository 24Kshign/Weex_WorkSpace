//
//  WXATScannerPlugin.m
//  WeexDemo
//
//  Created by HY on 2018/9/4.
//  Copyright © 2018年 taobao. All rights reserved.
//

#import "WXATScannerPlugin.h"
#import "WXScannerVC.h"

@interface WXATScannerPlugin ()

@property (nonatomic, strong) WXScannerVC *vc;
@end

@implementation WXATScannerPlugin

- (void)pluginDidLoadWithArgs:(NSArray *)args
{
    
}

- (void)pluginWillOpenInContainer:(UIViewController *)container withArg:(NSArray *)args
{
    WXScannerVC *vc = [WXScannerVC new];
    
    UINavigationController *rootVC = (UINavigationController *)[UIApplication sharedApplication].delegate.window.rootViewController;
    [rootVC pushViewController:vc animated:YES];
}

- (void)pluginWillClose
{
    
}

- (void)pluginDidUnload
{
    
}

- (CGRect)wantReactArea
{
    return CGRectZero;
}

@end

//
//  WXImagePicker.m
//  WeexDemo
//
//  Created by HY on 2018/10/8.
//  Copyright © 2018 mhc. All rights reserved.
//

#import "WXImagePicker.h"
#import "MHCB2BPhotoSelectorViewController.h"
#import "MHCB2BPrefixHeader.h"
#import "MHCB2BImageModel.h"
#import "MHCB2BUploadService.h"


@implementation WXImagePicker

WX_EXPORT_METHOD(@selector(selectPhoto:handler:))

- (void)selectPhoto:(NSString *)num handler:(WXModuleKeepAliveCallback)handler {
    MHCB2BPhotoSelectorViewController *vc = [[MHCB2BPhotoSelectorViewController alloc] initWithMaxImagesCount:num.integerValue delegate:nil];
    vc.photoWidth = 5000;
    vc.barItemTextColor = [UIColor colorWithHex:0x6C94F7];
    vc.naviTitleColor   = [UIColor whiteColor];
    vc.isSelectOriginalPhoto = YES;
    vc.allowPickingVideo     = NO;
    vc.didFinishPickingPhotosHandle = ^(NSArray<UIImage *> *photos, NSArray *assets, BOOL isSelectOriginalPhoto) {

        MHCB2BProgressHUD *hud = [MHCB2BProgressHUD createHUDWithText:@"上传中"];
        [hud show];
        
        NSMutableArray *images = [NSMutableArray array];
        for (UIImage *image in photos) {
            MHCB2BImageModel *model = [[MHCB2BImageModel alloc] init];
            model.image = image;
            [images addObject:model];
        }
        
        [MHCB2BUploadService uploadImages:images progress:^(CGFloat progress) {
        } success:^(NSArray *results) {
            [hud hide];
            NSMutableArray *urls = [NSMutableArray array];
            for (MHCB2BImageModel *model in results) {
                if (model.imageUrl) {
                    [urls addObject: model.imageUrl];
                }
            }
            
            if (handler) {
                handler(@{@"photos": urls}, YES);
            }
        } failure:^{
            [hud hide];
            if (handler) {
                handler(@{@"photos": @[]}, YES);
            }
        }];
    };
    [[MHCB2BUtils getCurrentViewController] presentViewController:vc animated:YES completion:^{
    }];
}

@end

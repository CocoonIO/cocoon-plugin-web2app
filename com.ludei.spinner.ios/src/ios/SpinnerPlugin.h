#import <Foundation/Foundation.h>
#import <Cordova/CDVPlugin.h>

@interface SpinnerPlugin : CDVPlugin {
    UIImage* image;
    UIImageView* _imageView;
}

@end
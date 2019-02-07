#import "SpinnerPlugin.h"
#import <Cordova/CDVViewController.h>

@implementation SpinnerPlugin

- (void)pluginInitialize {
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(webViewDidStartLoad:) name:CDVPluginResetNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(webViewDidFinishLoad:) name:CDVPageDidLoadNotification object:nil];
    
    image = [UIImage animatedImageNamed:@"spinner_" duration:1.0f];
    _imageView = [[UIImageView alloc] initWithFrame:[UIScreen mainScreen].bounds];
    _imageView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight | UIViewAutoresizingFlexibleBottomMargin |
                                    UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleRightMargin |
                                    UIViewAutoresizingFlexibleTopMargin | UIViewAutoresizingFlexibleBottomMargin;
    _imageView.contentMode = UIViewContentModeCenter;
    _imageView.alpha = 0.75f;
    _imageView.image = image;
    _imageView.backgroundColor = [UIColor blackColor];
}

- (void)webViewDidStartLoad:(NSNotification*)notification
{
    UIView* parentView = self.viewController.view;
    parentView.userInteractionEnabled = NO;
    [parentView addSubview:_imageView];
}

- (void)webViewDidFinishLoad:(NSNotification*)notification
{
    [_imageView removeFromSuperview];
    self.viewController.view.userInteractionEnabled = YES;
}

@end
//
//  CocoonWeb2App
//
//  Created by Imanol Fernandez @Ludei
//

#import "CocoonWeb2App.h"
#import "CocoonInjector.h"

@implementation CocoonWeb2App


-(void) pluginInitialize
{
    [NSURLProtocol registerClass:[CocoonInjector class]];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(pageDidLoad) name:CDVPageDidLoadNotification object:nil];
}

-(void) pageDidLoad
{
    NSString * script = @"(function(){if (window.cordova) return; var script = document.createElement(\"script\"); script.src = \"cordova.js\"; document.head.appendChild(script);})();";
    [self.webViewEngine evaluateJavaScript:script completionHandler:^(id result, NSError * error) {
        
    }];
}

-(void) dispose
{
    [[NSNotificationCenter defaultCenter] removeObserver:self name:CDVPageDidLoadNotification object:nil];
}

- (BOOL)shouldAllowNavigationToURL:(NSURL *)url
{
    return YES;
}

- (BOOL)shouldAllowRequestForURL:(NSURL *)url
{
    return YES;
}

@end
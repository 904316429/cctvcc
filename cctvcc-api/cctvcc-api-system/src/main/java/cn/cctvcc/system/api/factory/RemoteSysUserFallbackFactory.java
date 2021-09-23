//package cn.cctvcc.system.api.factory;
//
//import cn.cctvcc.core.domain.R;
//import cn.cctvcc.system.api.domain.LoginUser;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.openfeign.FallbackFactory;
//import org.springframework.stereotype.Component;
//
///**
// * @description:
// * @author: Jiang
// * @create: 2021-09-23 17:47
// */
//@Component
//public class RemoteSysUserFallbackFactory implements FallbackFactory<RemoteSysUserService> {
//
//    private static final Logger log = LoggerFactory.getLogger(RemoteSysUserFallbackFactory.class);
//
//    @Override
//    public RemoteSysUserService create(Throwable throwable)
//    {
//        log.error("用户服务调用失败:{}", throwable.getMessage());
//        return new RemoteSysUserService() {
//
//            @Override
//            public R<LoginUser> userInfo(String username)
//            {
//                return R.fail("获取用户失败:" + throwable.getMessage());
//            }
//        };
//    }
//}

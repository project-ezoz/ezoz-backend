package ezoz.backend_ezoz.api.login.service.social;

import ezoz.backend_ezoz.domain.member.constant.MemberType;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SocialLoginApiServiceFactory {

    private static Map<String, SocialLoginApiService> socialApiServices;

    public SocialLoginApiServiceFactory(Map<String, SocialLoginApiService> socialApiSerivces) {
        this.socialApiServices = socialApiSerivces;
    }

    public static SocialLoginApiService getSocialLoginApiService(MemberType memberType) {
        String socialApiServiceBeanName = "";

        if(MemberType.KAKAO.equals(memberType)) {
            socialApiServiceBeanName = "kakaoLoginApiServiceImpl";
        }

        if (MemberType.GOOGLE.equals(memberType)) {
            socialApiServiceBeanName = "googleLoginApiServiceImpl";
        }

        return socialApiServices.get(socialApiServiceBeanName);
    }

}

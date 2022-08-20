package ezoz.backend_ezoz.domain.member.constant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MemberType {
    GENERAL, KAKAO, GOOGLE;

    /**
     * 해당 문자열이 MemberType에 해당하는지 검사
     * @param type
     * @return
     */
    public static boolean isMemberType(String type) {
        List<MemberType> collect = Arrays.stream(MemberType.values())
                .filter(memberType -> !memberType.equals("GENERAL"))
                .filter(memberType -> memberType.name().equals(type))
                .collect(Collectors.toList());
        return collect.size() != 0;
    }

    public static MemberType from(String type) {
        return MemberType.valueOf(type.toUpperCase());
    }
}

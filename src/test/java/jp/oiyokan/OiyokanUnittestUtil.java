package jp.oiyokan;

import org.apache.olingo.server.api.ODataApplicationException;

import jp.oiyokan.common.OiyoInfo;

public class OiyokanUnittestUtil {
    public static OiyoInfo setupUnittestDatabase() throws ODataApplicationException {
        final OiyoInfo oiyoInfo = OiyokanEdmProvider.getOiyoInfoInstance();
        // 取得した OiyoInfo をそのまま返却.
        return oiyoInfo;
    }
}

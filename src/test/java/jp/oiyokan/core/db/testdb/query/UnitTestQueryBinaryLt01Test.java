/*
 * Copyright 2021 Toshiki Iga
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.oiyokan.core.db.testdb.query;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.olingo.server.api.ODataResponse;
import org.junit.jupiter.api.Test;

import jp.oiyokan.OiyokanUnittestUtil;
import jp.oiyokan.common.OiyoInfo;
import jp.oiyokan.common.OiyoUrlUtil;
import jp.oiyokan.util.OiyokanTestUtil;

/**
 * フィルタの型に着眼したテスト.
 */
class UnitTestQueryBinaryLt01Test {
    // SUBSTRING
    @Test
    void test01() throws Exception {
        @SuppressWarnings("unused")
        final OiyoInfo oiyoInfo = OiyokanUnittestUtil.getUnittestOiyoInfoInstance();

        final ODataResponse resp = OiyokanTestUtil.callGet( //
                "/ODataTest1", //
                OiyoUrlUtil.encodeUrlQuery("&$filter=Double1 lt 500 &$top=5 &$skip=2 &$select=ID &$orderby=ID asc"));
        final String result = OiyokanTestUtil.stream2String(resp.getContent());

        // System.err.println("result: " + result);
        assertEquals(
                "{\"@odata.context\":\"$metadata#ODataTest1\",\"value\":[{\"ID\":3},{\"ID\":4},{\"ID\":5},{\"ID\":6},{\"ID\":7}]}",
                result);
        assertEquals(200, resp.getStatusCode());
    }
}

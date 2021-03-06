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
package jp.oiyokan.unittest.db.build;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.olingo.server.api.ODataApplicationException;
import org.junit.jupiter.api.Test;

import jp.oiyokan.common.OiyoCommonJdbcUtil;
import jp.oiyokan.common.OiyoInfo;
import jp.oiyokan.common.OiyoInfoUtil;
import jp.oiyokan.data.OiyokanResourceSqlUtil;
import jp.oiyokan.dto.OiyoSettingsDatabase;

/**
 * テスト用の内部データベースを作成します。この内部データベースは動作の上で必要です。
 */
class Build00H2InterDbTest {
    private static final Log log = LogFactory.getLog(Build00H2InterDbTest.class);

    private static final String[][] OIYOKAN_FILE_SQLS = new String[][] { //
            /*
             * Oiyokan の基本機能を確認およびビルド時の JUnitテストで利用. 変更するとビルドが動作しなくなる場合あり.
             */
            { "oiyoUnitTestDb", "oiyokan-test-db-h2.sql" }, //

            /*
             * Sakila dvdrental サンプルDB の内容そのもの.
             */
            { "oiyoUnitTestDb", "sample-sakila-db-h2.sql" }, //
    };

    /**
     * postgres 接続環境が適切に存在する場合にのみ JUnit を実行。
     */
    @Test
    void test01() throws Exception {
        if (new File("./src/main/resources/db/oiyokan-internal.mv.db").exists()) {
            // すでにファイルが存在する場合は処理スキップ。
            log.info("TRACE: InternalTarget (テスト用内部データベース) ファイルはすでに存在するので作成をスキップ。");
            log.info("TRACE: InternalTarget (テスト用内部データベース) テスト用内部データベースの内容を更新する場合には、データベースファイルを削除して再度テスト実行してください。");
            return;
        }

        final OiyoInfo oiyoInfo = new OiyoInfo();
        oiyoInfo.setSettings(OiyoInfoUtil.loadOiyokanSettings(oiyoInfo));

        for (String[] sqlFileDef : OIYOKAN_FILE_SQLS) {
            log.info("OData: load: internal db:" + sqlFileDef[0] + ", sql: " + sqlFileDef[1]);

            OiyoSettingsDatabase lookDatabase = OiyoInfoUtil.getOiyoDatabaseByName(oiyoInfo, sqlFileDef[0]);

            try (Connection connLoookDatabase = OiyoCommonJdbcUtil.getConnection(lookDatabase)) {
                final String[] sqls = OiyokanResourceSqlUtil.loadOiyokanResourceSql("/oiyokan/sql/" + sqlFileDef[1]);
                for (String sql : sqls) {
                    try (var stmt = connLoookDatabase.prepareStatement(sql.trim())) {
                        stmt.executeUpdate();
                        connLoookDatabase.commit();
                    } catch (SQLException ex) {
                        log.error("UNEXPECTED: Fail to execute SQL for local internal table(2): " + ex.toString());
                        throw new ODataApplicationException(
                                "UNEXPECTED: Fail to execute SQL for local internal table(2)", 500, Locale.ENGLISH);
                    }
                }

                log.info("OData: load: internal db: end: " + sqlFileDef[0] + ", sql: " + sqlFileDef[1]);
            } catch (SQLException ex) {
                log.error("UNEXPECTED: Fail to execute Dabaase: " + ex.toString());
                throw new ODataApplicationException("UNEXPECTED: Fail to execute Dabaase", 500, Locale.ENGLISH);
            }
        }
    }
}

package jp.app.ctrl;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.oiyokan.OiyokanConstants;
import jp.oiyokan.unittest.OiyokanOdata4Register;
import jp.oiyokan.unittest.OiyokanUnittestConstants;

@Controller
public class ThIndexCtrl {
    @RequestMapping(value = { "/", "/index.html" })
    public String oiyokanUnittest(Model model) throws IOException {
        model.addAttribute("odataRootpath", OiyokanOdata4Register.ODATA_ROOTPATH);

        IndexBean index = new IndexBean();
        model.addAttribute("indexBean", index);

        return "index";
    }

    public static class IndexBean {
        public String getVersion() {
            return OiyokanConstants.VERSION;
        }

        public String getUnittestVersion() {
            return OiyokanUnittestConstants.VERSION;
        }
    }
}

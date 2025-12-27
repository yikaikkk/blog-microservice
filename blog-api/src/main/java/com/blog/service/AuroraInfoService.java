package com.blog.service;

import com.blog.model.dto.AboutDTO;
import com.blog.model.dto.AuroraAdminInfoDTO;
import com.blog.model.dto.AuroraHomeInfoDTO;
import com.blog.model.dto.WebsiteConfigDTO;
import com.blog.model.vo.AboutVO;
import com.blog.model.vo.WebsiteConfigVO;

public interface AuroraInfoService {

    void report();

    AuroraHomeInfoDTO getAuroraHomeInfo();

    AuroraAdminInfoDTO getAuroraAdminInfo();

    void updateWebsiteConfig(WebsiteConfigVO websiteConfigVO);

    WebsiteConfigDTO getWebsiteConfig();

    void updateAbout(AboutVO aboutVO);

    AboutDTO getAbout();

}

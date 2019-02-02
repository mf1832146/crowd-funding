package com.agile.crowdfunding.controller;

import com.agile.crowdfunding.dao.ImageRepository;
import com.agile.crowdfunding.entity.Image;
import com.agile.crowdfunding.service.ProjectService;
import com.agile.crowdfunding.util.AuthorizationUtils;
import com.agile.crowdfunding.util.RandomUtils;
import com.agile.crowdfunding.vo.ProjectInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/launch")
public class LaunchProjectController {
    private static Logger log = LoggerFactory.getLogger(LaunchProjectController.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    AuthorizationUtils auth;

    @Autowired
    ImageRepository newImageDao;

    @Value("${image.upload.path}")
    private String imageUploadPath;

    @Value("${image.url}")
    private String imageUrl;




    @RequestMapping("/toLaunch")
    public String toLaunch(HttpSession session) {
        if (!auth.check(session))
            return "redirect:/login/toLogin";
        return "fore/launch/launchproject";
    }

    @RequestMapping(value = "/launchOpen", method = RequestMethod.POST)
    @ResponseBody
    public String launchOpen(HttpSession session, HttpServletRequest request, ProjectInfoVo projectInfoVo)
            throws IllegalStateException, IOException {

        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        log.info(projectInfoVo.toString());
        log.info(params.getParameter("name"));

        if (!auth.check(session))
            return "redirect:/login/toLogin";

        String uid = (String) session.getAttribute("myId");

        // 为projectInfoVo赋值
        projectInfoVo.setProName(params.getParameter("name"));
        projectInfoVo.setProIdNumber(params.getParameter("idNumber"));
        projectInfoVo.setProPhoneNumber(params.getParameter("phoneNumber"));
        projectInfoVo.setProTitle(params.getParameter("title"));
        projectInfoVo.setProPurpose(params.getParameter("purpose"));
        projectInfoVo.setProCoverStory(params.getParameter("coverStory"));
        projectInfoVo.setProTargetMoney(Integer.valueOf(params.getParameter("targetMoney")));
        projectInfoVo.setProLastTime(Integer.valueOf(params.getParameter("lastTime")));
        projectInfoVo.setProLocation(params.getParameter("province") + params.getParameter("city"));
        projectInfoVo.setProLabels(params.getParameter("labels"));
        projectInfoVo.setProTypeOfReward(Integer.valueOf(params.getParameter("rewardType")));
        projectInfoVo.setProAmountForReward(Integer.valueOf(params.getParameter("amount4Reward")));
        projectInfoVo.setProReward(params.getParameter("reward"));
        log.info(projectInfoVo.toString());
        String myPid = projectService.launchProject(uid, projectInfoVo);

        // 图片表
        // 封面图片——1
        // 描述图片——2
        // 身份证图片——3
        Image newImage = new Image();
        // 描述图片
        log.info("描述图片：");
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        //String prePath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "images/";
        //String prePath = "/var/spring/uploaded_files/";
        String prePath = imageUploadPath;
        File decDir = new File(prePath);
        if (!decDir.exists())
            decDir.mkdirs();
        MultipartFile file = null;
        log.info(Integer.toString(files.size()));
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                log.info(file.getOriginalFilename());
                String filename = UUID.randomUUID() + file.getOriginalFilename();
                String locPath = prePath + filename;
                String webPath = imageUrl + filename;

                log.info(locPath);
                file.transferTo(new File(locPath));
                // 插入数据库图片表
                newImage.setName(webPath);
                newImage.setType(2);
                newImage.setProjectId(myPid);
                newImageDao.save(newImage);
            }
        }
        //保存reward信息

        return "redirect:/fore/index";
    }
}


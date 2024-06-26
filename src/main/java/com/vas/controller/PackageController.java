/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vas.controller;


import com.dao.PackageDao;
import com.dao.ReceiptDao;
import com.dao.VASServiceDao;
import com.model.UserInformationModel;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author nabin
 */
@Controller
public class PackageController {
    private static final Logger logger = LoggerFactory.getLogger(PackageController.class);
    @RequestMapping(value = "/package/list", method = RequestMethod.GET)
    public String packagelist(Locale locale, Model model) throws SQLException {
//System.out.println("nabin code==");
        logger.info("Getting package List", locale);
        PackageDao dao = new PackageDao();
        List<Map<String, Object>> list = null;
        try {
            list = dao.getPackageList();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute("fx", "Package List");
        model.addAttribute("data_list", list);
        VASServiceDao VASSER = new VASServiceDao();
        model.addAttribute("VASSer_list", VASSER.getVasServiceList());
      
        
        //COADao COA = new COADao();
        //model.addAttribute("COA_list", COA.getCOAlist());

        return "package/list";
    }
    
    @RequestMapping(value = "/package/saveJS", method = RequestMethod.POST)
    @ResponseBody
    public String saveJSPackage(String PACKAGE_TYPE, String DESCRIPTION, String SERVICE_CODE, HttpSession session, Model model, Locale locale) {
        logger.info("Save package {}.", locale);
        PackageDao dao = new PackageDao();
//        System.out.println("uddate Servce code==" + SERVICE_CODE);
        UserInformationModel userinfo = (UserInformationModel) session.getAttribute("UserList");
        String USER = userinfo.getUSER_ID();
        model.addAttribute("fx", "Package controller list ");
        model.addAttribute("userName", "NEPal");
        String msg = null;
        try {
            msg = dao.savePackage(PACKAGE_TYPE, DESCRIPTION,SERVICE_CODE, USER);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return msg;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "dialogpackage")
    public String dialogpackage(Model model, Locale locale) {
        return "package/packagedialog";

    }
    
    @RequestMapping(value = "/package/update", method = RequestMethod.POST)
    @ResponseBody
    public String updatePackage(String PACKAGE_TYPE, String DESCRIPTION,String SERVICE_CODE, HttpSession session, Model model, Locale locale) {

        logger.info("Updata Package {}.", locale);
        PackageDao dao = new PackageDao();
//        System.out.println("uddate Servce code==" + SERVICE_CODE);
        UserInformationModel userinfo = (UserInformationModel) session.getAttribute("UserList");
        String USER = userinfo.getUSER_ID();
        model.addAttribute("fx", "Package controller list ");
        model.addAttribute("userName", "NEPal");
        String msg = null;
        try {
            msg = dao.updatePackage(PACKAGE_TYPE, DESCRIPTION,SERVICE_CODE, USER);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return msg;
    }

    @RequestMapping(value = "/package/delete", method = RequestMethod.POST)
    @ResponseBody
    public String packageDelete(String PACKAGE_TYPE, Model model, Locale locale) {
        logger.info("delete package", locale);
        PackageDao dao = new PackageDao();
//        System.out.println("delete service code==" + SERVICE_CODE);
        String msg = null;
        try {
            msg = dao.DeletePackage(PACKAGE_TYPE);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return msg;

    }
    
    
 // getting list of all SP wise Service List
    @ResponseBody
    @RequestMapping(value = "/package/getlist", method = RequestMethod.GET)
    public List<Map<String,Object>> getSpdue(HttpSession session)
            throws SQLException {
    	 PackageDao dao = new PackageDao();
        return dao.getPackageList();
    }

    
}

package com.vas.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.BankDao;
import com.dao.CommonDateDao;
import com.dao.CommonMenuDao;
import com.dao.ItemDao;
import com.dao.SpDao;
import com.dao.VASServiceDao;
import com.model.MenuAccess;
import com.model.UserInformationModel;

@Controller
public class ReportMenuController {

    private static final String classname = "../report/list";

    @RequestMapping(value = "/report/spbalance", method = RequestMethod.GET)
    public String menuList(Locale locale, Model model, HttpServletRequest request)
            throws Exception {
        String url = request.getServletPath();

        UserInformationModel user = (UserInformationModel) request.getSession().getAttribute("UserList");
        MenuAccess menuaccess = CommonMenuDao.checkAccess(user.getROLE_CODE(), ".." + url);
        if (menuaccess == null || menuaccess.getADD_FLAG().equals("N")) {
            model.addAttribute("fx", "Unauthorized Page for this role!!");
            return "/home";
        }
        model.addAttribute("MENU_ACCESS", menuaccess);
        model.addAttribute("fx", "Report for SP balance");

        // getting list of paramater required
        SpDao sp = new SpDao();
        model.addAttribute("Sp_list", sp.getSpList());
        VASServiceDao VASSER = new VASServiceDao();
        model.addAttribute("VASSer_list", VASSER.getVasServiceList());
        ItemDao ITEM = new ItemDao();
        model.addAttribute("ITEMwotax_list", ITEM.getItemListwotax());
        BankDao bank = new BankDao();

        CommonDateDao DAT = new CommonDateDao();
        model.addAttribute("Date_list", DAT.getDateList());

        return "report/spbalance";

    }

    @RequestMapping(value = "/report/spinvoice", method = RequestMethod.GET)
    public String menuinvoiceList(Locale locale, Model model, HttpServletRequest request)
            throws Exception {
        String url = request.getServletPath();

        UserInformationModel user = (UserInformationModel) request.getSession().getAttribute("UserList");
        MenuAccess menuaccess = CommonMenuDao.checkAccess(user.getROLE_CODE(), ".." + url);
        if (menuaccess == null || menuaccess.getADD_FLAG().equals("N")) {
            model.addAttribute("fx", "Unauthorized Page for this role!!");
            return "/home";
        }
        model.addAttribute("MENU_ACCESS", menuaccess);
        model.addAttribute("fx", "Report for VAS Invoice");

        // getting list of paramater required
        SpDao sp = new SpDao();
        model.addAttribute("Sp_list", sp.getSpList());
        BankDao bank = new BankDao();

        VASServiceDao VASSER = new VASServiceDao();
        model.addAttribute("VASSer_list", VASSER.getVasServiceList());

        CommonDateDao DAT = new CommonDateDao();
        model.addAttribute("Date_list", DAT.getDateList());

        CommonDateDao mon = new CommonDateDao();
        model.addAttribute("Mon_list", mon.getNepMonthList());

        return "report/spinvoice";

    }

    @RequestMapping(value = "/report/sprevenue", method = RequestMethod.GET)
    public String menurevenueList(Locale locale, Model model, HttpServletRequest request)
            throws Exception {
        String url = request.getServletPath();

        UserInformationModel user = (UserInformationModel) request.getSession().getAttribute("UserList");
        MenuAccess menuaccess = CommonMenuDao.checkAccess(user.getROLE_CODE(), ".." + url);
        if (menuaccess == null || menuaccess.getADD_FLAG().equals("N")) {
            model.addAttribute("fx", "Unauthorized Page for this role!!");
            return "/home";
        }
        model.addAttribute("MENU_ACCESS", menuaccess);
        model.addAttribute("fx", "Report for Overall VAS Revenue");

        // getting list of paramater required
        SpDao sp = new SpDao();
        model.addAttribute("Sp_list", sp.getSpList());

        VASServiceDao VASSER = new VASServiceDao();
        model.addAttribute("VASSer_list", VASSER.getVasServiceList());

        ItemDao ITEM = new ItemDao();
        model.addAttribute("ITEMwotax_list", ITEM.getItemListwotax());

        BankDao bank = new BankDao();
        CommonDateDao DAT = new CommonDateDao();
        model.addAttribute("Date_list", DAT.getDateList());

        CommonDateDao mon = new CommonDateDao();
        model.addAttribute("Mon_list", mon.getNepMonthList());

        return "report/sprevenue";

    }
    
    @RequestMapping(value = "/report/spsmsrevenue", method = RequestMethod.GET)
    public String menusmsrevenueList(Locale locale, Model model, HttpServletRequest request)
            throws Exception {
        String url = request.getServletPath();

        UserInformationModel user = (UserInformationModel) request.getSession().getAttribute("UserList");
        MenuAccess menuaccess = CommonMenuDao.checkAccess(user.getROLE_CODE(), ".." + url);
        if (menuaccess == null || menuaccess.getADD_FLAG().equals("N")) {
            model.addAttribute("fx", "Unauthorized Page for this role!!");
            return "/home";
        }
        model.addAttribute("MENU_ACCESS", menuaccess);
        model.addAttribute("fx", "Report for SMS Revenue");

        // getting list of paramater required
        SpDao sp = new SpDao();
        model.addAttribute("Sp_list", sp.getSpList());

        VASServiceDao VASSER = new VASServiceDao();
        model.addAttribute("VASSer_list", VASSER.getVasServiceList());

        ItemDao ITEM = new ItemDao();
        model.addAttribute("ITEMwotax_list", ITEM.getItemListwotax());

        BankDao bank = new BankDao();
        CommonDateDao DAT = new CommonDateDao();
        model.addAttribute("Date_list", DAT.getDateList());

        CommonDateDao mon = new CommonDateDao();
        model.addAttribute("Mon_list", mon.getNepMonthList());

        return "report/spsmsrevenue";

    }

    @RequestMapping(value = "/report/spsales", method = RequestMethod.GET)
    public String menusalesList(Locale locale, Model model, HttpServletRequest request)
            throws Exception {
        String url = request.getServletPath();

        UserInformationModel user = (UserInformationModel) request.getSession().getAttribute("UserList");
        MenuAccess menuaccess = CommonMenuDao.checkAccess(user.getROLE_CODE(), ".." + url);
        if (menuaccess == null || menuaccess.getADD_FLAG().equals("N")) {
            model.addAttribute("fx", "Unauthorized Page for this role!!");
            return "/home";
        }
        model.addAttribute("MENU_ACCESS", menuaccess);
        model.addAttribute("fx", "Report for Sales");

        // getting list of paramater required
        SpDao sp = new SpDao();
        model.addAttribute("Sp_list", sp.getSpList());

        VASServiceDao VASSER = new VASServiceDao();
        model.addAttribute("VASSer_list", VASSER.getVasServiceList());

        ItemDao ITEM = new ItemDao();
        model.addAttribute("ITEMwotax_list", ITEM.getItemListwotax());

        BankDao bank = new BankDao();

        CommonDateDao DAT = new CommonDateDao();
        model.addAttribute("Date_list", DAT.getDateList());

        return "report/spsales";
    }

    @RequestMapping(value = "/report/spmaster", method = RequestMethod.GET)
    public String menumasterList(Locale locale, Model model, HttpServletRequest request)
            throws Exception {
        String url = request.getServletPath();

        UserInformationModel user = (UserInformationModel) request.getSession().getAttribute("UserList");
        MenuAccess menuaccess = CommonMenuDao.checkAccess(user.getROLE_CODE(), ".." + url);
        if (menuaccess == null || menuaccess.getADD_FLAG().equals("N")) {
            model.addAttribute("fx", "Unauthorized Page for this role!!");
            return "/home";
        }
        model.addAttribute("MENU_ACCESS", menuaccess);
        model.addAttribute("fx", "Report for SP Master");

        // getting list of paramater required
        SpDao sp = new SpDao();
        model.addAttribute("Sp_list", sp.getSpList());

        VASServiceDao VASSER = new VASServiceDao();
        model.addAttribute("VASSer_list", VASSER.getVasServiceList());

        ItemDao ITEM = new ItemDao();
        model.addAttribute("ITEMwotax_list", ITEM.getItemListwotax());

        BankDao bank = new BankDao();
        CommonDateDao DAT = new CommonDateDao();
        model.addAttribute("Date_list", DAT.getDateList());

        CommonDateDao mon = new CommonDateDao();
        model.addAttribute("Mon_list", mon.getNepMonthList());

        return "report/spmaster";
    }
    
    @RequestMapping(value = "/report/spsubrevenue", method = RequestMethod.GET)
    public String menusubrevenueList(Locale locale, Model model, HttpServletRequest request)
            throws Exception {
        String url = request.getServletPath();

        UserInformationModel user = (UserInformationModel) request.getSession().getAttribute("UserList");
        MenuAccess menuaccess = CommonMenuDao.checkAccess(user.getROLE_CODE(), ".." + url);
        if (menuaccess == null || menuaccess.getADD_FLAG().equals("N")) {
            model.addAttribute("fx", "Unauthorized Page for this role!!");
            return "/home";
        }
        model.addAttribute("MENU_ACCESS", menuaccess);
        model.addAttribute("fx", "VAS Subscription Revenue Report");

        // getting list of paramater required
        SpDao sp = new SpDao();
        model.addAttribute("Sp_list", sp.getSpList());

        VASServiceDao VASSER = new VASServiceDao();
        model.addAttribute("VASSer_list", VASSER.getVasServiceList());

        ItemDao ITEM = new ItemDao();
        model.addAttribute("ITEMwotax_list", ITEM.getItemListwotax());

        BankDao bank = new BankDao();
        CommonDateDao DAT = new CommonDateDao();
        model.addAttribute("Date_list", DAT.getDateList());

        CommonDateDao mon = new CommonDateDao();
        model.addAttribute("Mon_list", mon.getNepMonthList());

        return "report/spsubrevenue";

    }
    
    @RequestMapping(value = "/report/sppayable", method = RequestMethod.GET)
    public String menupayableList(Locale locale, Model model, HttpServletRequest request)
            throws Exception {
        String url = request.getServletPath();

        UserInformationModel user = (UserInformationModel) request.getSession().getAttribute("UserList");
        MenuAccess menuaccess = CommonMenuDao.checkAccess(user.getROLE_CODE(), ".." + url);
        if (menuaccess == null || menuaccess.getADD_FLAG().equals("N")) {
            model.addAttribute("fx", "Unauthorized Page for this role!!");
            return "/home";
        }
        model.addAttribute("MENU_ACCESS", menuaccess);
        model.addAttribute("fx", "Report for Payable");

        // getting list of paramater required
        SpDao sp = new SpDao();
        model.addAttribute("Sp_list", sp.getSpList());
        BankDao bank = new BankDao();

        VASServiceDao VASSER = new VASServiceDao();
        model.addAttribute("VASSer_list", VASSER.getVasServiceList());

        CommonDateDao DAT = new CommonDateDao();
        model.addAttribute("Date_list", DAT.getDateList());

        CommonDateDao mon = new CommonDateDao();
        model.addAttribute("Mon_list", mon.getNepMonthList());

        return "report/sppayable";

    }
}

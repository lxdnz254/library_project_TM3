package com.lxdnz.bit794.tm3.library_project.web.support;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.lxdnz.bit794.tm3.library_project.web.support.Message.MESSAGE_ATTRIBUTE;

public class MessageHelper {

    private MessageHelper() {

    }

    public static void addSuccessAttribute(RedirectAttributes ra, String message) {
        addAttribute(ra, message);
    }

    public static void addErrorAttribute(RedirectAttributes ra, String message) {
        addAttribute(ra, message);
    }

    public static void addInfoAttribute(RedirectAttributes ra, String message) {
        addAttribute(ra, message);
    }

    public static void addWarningAttribute(RedirectAttributes ra, String message) {
        addAttribute(ra, message);
    }

    private static void addAttribute(RedirectAttributes ra, String message) {
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE, message);
    }

    public static void addSuccessAttribute(Model model, String message) {
        addAttribute(model, message);
    }

    public static void addErrorAttribute(Model model, String message) {
        addAttribute(model, message);
    }

    public static void addInfoAttribute(Model model, String message) {
        addAttribute(model, message);
    }

    public static void addWarningAttribute(Model model, String message) {
        addAttribute(model, message);
    }

    private static void addAttribute(Model model, String message) {
        model.addAttribute(MESSAGE_ATTRIBUTE, message);
    }
}

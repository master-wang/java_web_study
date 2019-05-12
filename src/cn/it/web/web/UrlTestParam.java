package cn.it.web.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UrlTestParam {
    void addUrl(HttpServletRequest req, HttpServletResponse resp);

    void updateUrl(HttpServletRequest req, HttpServletResponse resp);

    void deleteUrl(HttpServletRequest req, HttpServletResponse resp);

    void findUrl(HttpServletRequest req, HttpServletResponse resp);
}

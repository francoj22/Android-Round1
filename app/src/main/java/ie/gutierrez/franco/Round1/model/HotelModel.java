package ie.gutierrez.franco.Round1.model;

import java.util.ArrayList;

/**
 * This class will keep all the JsonObjects values.
 * Created by Franco on 20/12/2015.
 */
public class HotelModel {
    private String url, filePath, nameSpace, cache, params, pageTitle;
    private ArrayList<String> templateLastUpdated;

    public HotelModel() {
    }

    public HotelModel(String url, String filePath, String nameSpace, String cache, String params,
                      String pageTitle, ArrayList<String> templateLastUpdated) {
        this.url = url;
        this.filePath = filePath;
        this.nameSpace = nameSpace;
        this.cache = cache;
        this.params = params;
        this.pageTitle = pageTitle;
        this.templateLastUpdated = templateLastUpdated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public ArrayList<String> getTemplateLastUpdated() {
        return templateLastUpdated;
    }

    public void setTemplateLastUpdated(ArrayList<String> templateLastUpdated) {
        this.templateLastUpdated = templateLastUpdated;
    }




}
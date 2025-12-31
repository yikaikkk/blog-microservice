package com.blog.config;

// 抽象类，定义公共属性和方法
public interface AbstractAiConfig {

   

    public String getModel() ;  

    public void setModel(String model) ;

    public String getApiKey() ;

    public void setApiKey(String apiKey) ;

    public String getName() ;

    public void setName(String name) ;
        
    public String getBaseUrl() ;

    public void setBaseUrl(String baseUrl) ;
}

package com.shurik.pizzaplanet.pizzasearch;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

class Availability{
    @JsonProperty("Intervals")
    public ArrayList<Interval> intervals;
    @JsonProperty("Monday")
    public boolean monday;
    @JsonProperty("Tuesday")
    public boolean tuesday;
    @JsonProperty("Wednesday")
    public boolean wednesday;
    @JsonProperty("Thursday")
    public boolean thursday;
    @JsonProperty("Friday")
    public boolean friday;
    @JsonProperty("Saturday")
    public boolean saturday;
    @JsonProperty("Sunday")
    public boolean sunday;
    @JsonProperty("Everyday")
    public boolean everyday;
}

class Category{
    @JsonProperty("class")
    public String myclass;
    public String name;
}

class CompanyMetaData{
    public String id;
    public String name;
    public String address;
    @JsonProperty("Phones")
    public ArrayList<Phone> phones;
    @JsonProperty("Categories")
    public ArrayList<Category> categories;
    @JsonProperty("Hours")
    public Hours hours;
    public String url;
}

class Feature{
    public String type;
    public Geometry geometry;
    public Properties properties;
}

class Geometry{
    public String type;
    public ArrayList<Double> coordinates;
}

class Hours{
    public String text;
    @JsonProperty("Availabilities")
    public ArrayList<Availability> availabilities;
}

class Interval{
    public String from;
    @JsonProperty("to")
    public String myto;
}

class Phone{
    public String type;
    public String formatted;
}

class Properties{
    @JsonProperty("ResponseMetaData")
    public ResponseMetaData responseMetaData;
    public String name;
    public String description;
    public ArrayList<ArrayList<Double>> boundedBy;
    @JsonProperty("CompanyMetaData")
    public CompanyMetaData companyMetaData;
}

class ResponseMetaData{
    @JsonProperty("SearchResponse")
    public SearchResponse searchResponse;
    @JsonProperty("SearchRequest")
    public SearchRequest searchRequest;
}

public class Root{
    public String type;
    public Properties properties;
    public ArrayList<Feature> features;
}

class SearchRequest{
    public String request;
    public int skip;
    public int results;
    public ArrayList<ArrayList<Double>> boundedBy;
}

class SearchResponse{
    public int found;
    public String display;
    public ArrayList<ArrayList<Double>> boundedBy;
}



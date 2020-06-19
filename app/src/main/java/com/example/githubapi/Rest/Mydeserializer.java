package com.example.githubapi.Rest;

import com.example.githubapi.model.GitHubUser;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;

public class Mydeserializer<T> implements JsonDeserializer<T> {
    @Override
    public T deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        JsonElement content = je.getAsJsonObject().get("items");

//            T result = new Gson().fromJson(content, type);
        Type collectionType = new TypeToken<Collection<GitHubUser>>(){}.getType();
        Collection<GitHubUser> enums = new Gson().fromJson(content, collectionType);
        return (T) enums;
    }}
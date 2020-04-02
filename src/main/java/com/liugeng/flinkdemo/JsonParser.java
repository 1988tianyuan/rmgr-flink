package com.liugeng.flinkdemo;

import java.io.BufferedReader;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class JsonParser {
	private static final Gson gson = new Gson().newBuilder().serializeNulls().disableHtmlEscaping().create();
	
	public static <I, O> O typeConvert(I input, TypeToken<O> typeToken) {
		return gson.fromJson(gson.toJsonTree(input), typeToken.getType());
	}

	public static String objectToJson(Object object) {
		return gson.toJson(object);
	}

	public static <T> T jsonToObject(String jsonStr, Class<T> clazz) {
		return gson.fromJson(jsonStr, clazz);
	}

	public static <T> T jsonToObject(String jsonStr, TypeToken<T> typeToken) {
		return gson.fromJson(jsonStr, typeToken.getType());
	}

	public static <T> T jsonObjectToObject(JsonObject jsonObject, TypeToken<T> typeToken) {
		return gson.fromJson(jsonObject, typeToken.getType());
	}

	public static <T> T jsonObjectToObject(JsonObject jsonObject, Class<T> clazz) {
		return gson.fromJson(jsonObject, clazz);
	}

	public static JsonObject jsonToJsonObject(String jsonStr) {
		return gson.fromJson(jsonStr, JsonObject.class);
	}

	public static <T> T jsonElementToObject(JsonElement jsonElement, Class<T> clazz) {
		return gson.fromJson(jsonElement, clazz);
	}

	public static <T> T jsonElementToObject(JsonElement jsonElement, TypeToken<T> typeToken) {
		return gson.fromJson(jsonElement, typeToken.getType());
	}

	public static <T> T readerToObject(BufferedReader reader, Class<T> clazz) {
		JsonReader jsonReader = new JsonReader(reader);
		jsonReader.setLenient(true);
		return gson.fromJson(jsonReader, clazz);
	}

}

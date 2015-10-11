package com.thechord.chord.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neway on 2015/8/20.
 */
public class JSONUtil<T> {


    public T getInstanceFromJsonObject(JSONObject jsonObj, Class<?> instance) {
        Object obj = null;
        try {
            obj = instance.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (obj == null) {
            return null;
        }

        Field[] instanceFields = instance.getDeclaredFields();
        for (Field field : instanceFields) {

            String jsonField = field.getName();
            String className = null;
            JsonFieldAnnotation annotation = field.getAnnotation(JsonFieldAnnotation.class);
            if (annotation != null) {
                if (!"".equals(annotation.value())) {
                    jsonField = annotation.value();
                }

                if (!"".equals(annotation.className())) {
                    className = annotation.className();
                }
            }


            try {
                field.setAccessible(true);

                if (className != null) {
                    field.set(obj,getInstanceFromJsonObject(jsonObj.getJSONObject(jsonField),Class.forName(className).newInstance().getClass()));
                    continue;
                }

                if (field.getType() == String.class) {
                    field.set(obj, jsonObj.getString(jsonField));
                } else if (field.getType() == Long.class) {
                    field.setLong(obj, jsonObj.getLong(jsonField));
                } else if (field.getType() == int.class) {
                    field.setInt(obj, jsonObj.getInt(jsonField));
                } else if (field.getType() == float.class) {
                    field.setDouble(obj, jsonObj.getDouble(jsonField));
                } else if (field.getType() == double.class) {
                    field.setDouble(obj, jsonObj.getDouble(jsonField));
                } else if (field.getType() == boolean.class) {
                    field.setBoolean(obj, jsonObj.getBoolean(jsonField));
                } else {
                    field.set(obj, jsonObj.get(jsonField));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }
        return (T) obj;
    }

    public List<T> getInstatnceFromJSONObject(JSONArray jsonArray, Class<T> instance) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                list.add(getInstanceFromJsonObject(jsonArray.getJSONObject(i), instance));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}




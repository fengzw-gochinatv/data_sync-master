package com.gochinatv.datasync.util;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class HttpClientTools {

	public static String textformat = "utf8";

	@SuppressWarnings("unchecked")
	public static List<String> getContent(String url) {
		List<String> content = null;
		try {

			BasicHttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setSoTimeout(httpParams, 2 * 60 * 1000);
			HttpClient client = new DefaultHttpClient(httpParams);
			HttpGet get = new HttpGet(url);
			get.setHeader("Content-Type", "application/x-www-form-urlencoded");
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream instreams = entity.getContent();
				content = convertStreamToStringList(instreams);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	@SuppressWarnings("unchecked")
	public static List<String> post_comment(HttpPost httpPost) throws Exception {
		List<String> content = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			// 构造一个post对象
			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instreams = entity.getContent();
				content = HttpClientTools.convertStreamToStringList(instreams);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	@SuppressWarnings("unchecked")
	public static List<String> getContentByPost(String url) {
		List<String> content = null;
		try {
			BasicHttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setSoTimeout(httpParams, 2 * 60 * 1000);
			HttpClient client = new DefaultHttpClient(httpParams);
			HttpPost post = new HttpPost(url);
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instreams = entity.getContent();
				content = convertStreamToStringList(instreams);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public static String Post(String url, Map<String, String> heads,
			Map<String, String> params)throws Exception {
		try {
			BasicHttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setSoTimeout(httpParams, 2 * 60 * 1000);

			HttpClient client = new DefaultHttpClient(httpParams);
			HttpPost post = new HttpPost(url);

			if (heads != null) {
				for (String h : heads.keySet()) {
					post.setHeader(h, heads.get(h));
				}
			}

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (params != null) {
				for (String key : params.keySet()) {
					nvps.add(new BasicNameValuePair(key, params.get(key)));
				}
			}

			post.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return EntityUtils.toString(entity).trim();
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}

	public static String PostJSON(String url, String json,
			Map<String, String> heads) {
		try {
			BasicHttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setSoTimeout(httpParams, 2 * 60 * 1000);

			HttpClient client = new DefaultHttpClient(httpParams);
			HttpPost post = new HttpPost(url);

			if (heads != null) {
				for (String h : heads.keySet()) {
					post.setHeader(h, heads.get(h));
				}
			}

			StringEntity se = new StringEntity(json, "utf-8");
			post.setEntity(se);

			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return EntityUtils.toString(entity).trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String Get(String url, Map<String, String> heads) {

		HttpGet httpget = new HttpGet(url);

		if (heads != null) {
			for (String h : heads.keySet()) {
				httpget.setHeader(h, heads.get(h));
			}
		}
		HttpClient client = new DefaultHttpClient();

		try {
			HttpResponse response = client.execute(httpget);

			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity).trim();

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	public static String Upload(String url, File f, String fileName,
			Map<String, String> heads) {

		HttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(url);

		FileBody fileBody = new FileBody(f);
		StringBody stringBody = null;
		try {
			stringBody = new StringBody(fileName);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		MultipartEntity reqEntity = new MultipartEntity();
		reqEntity.addPart("fileData", fileBody);
		reqEntity.addPart("fileDataFileName", stringBody);
		httppost.setEntity(reqEntity);

		// 执行
		try {
			HttpResponse response = httpclient.execute(httppost);
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				HttpEntity entity = response.getEntity();
				// 显示内容
				if (entity != null) {
					String context = EntityUtils.toString(entity).trim();
					System.out.println(context);
					return context;
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String Upload(String url, File f,
			Map<String, String> heads,Map<String, String> param) {

		HttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(url);

		MultipartEntity reqEntity = new MultipartEntity();
		
		if(f!=null){
			FileBody fileBody = new FileBody(f);
			reqEntity.addPart("fileData", fileBody);
		}
		StringBody stringBody = null;
		for(String para:param.keySet()){
			try {
				stringBody = new StringBody(param.get(para));
				reqEntity.addPart(para, stringBody);
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		
		httppost.setEntity(reqEntity);

		// 执行
		try {
			HttpResponse response = httpclient.execute(httppost);
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				HttpEntity entity = response.getEntity();
				// 显示内容
				if (entity != null) {
					String context = EntityUtils.toString(entity).trim();
					System.out.println(context);
					return context;
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List convertStreamToStringList(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				Charset.forName(textformat)));
		Vector list = new Vector();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				list.addElement(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
}

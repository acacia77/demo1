package com.yunagile.system;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yunagile.common.util.JsonHelper;
import com.yunagile.system.utils.bussiness.KVService;

public class TestKVService implements KVService{
	private Map<String,Integer> p = new HashMap<String,Integer>();
	private File file =  null;
	public TestKVService() {
		file = new File(System.getProperty("java.io.tmpdir")+"kvservice_test.json");
		if(file.exists()) {
			FileInputStream in = null;
			String a = null;
			try {
				a = readString(new FileInputStream(file));
			} catch (FileNotFoundException e) { 
				e.printStackTrace();
			} catch (IOException e) { 
				e.printStackTrace();
			} finally {
				if(in != null) {
					try {
						in.close();
					} catch (IOException e) { 
					}
				}
			}
			if(a!=null) {
				p.putAll(JsonHelper.fromJson(a, new TypeReference<Map<String,Integer>>() {
				}));
			}
		}
		
	}
	@Override
	public String nextSequence(String key, String format) { 
		Integer a = null;
		synchronized (key.intern()) {
			try {
				a = p.get(key);
				if(a==null){
					a = 1;
					p.put(key, a);
				}else{
					a = a+1;
					p.put(key, a);
				}
				FileOutputStream out = null;
				try {
					out = new FileOutputStream(file);
					out.write(JsonHelper.toJson(p).getBytes("utf-8"));
				} catch (FileNotFoundException e) { 
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}finally{
				 
			} 
		}
		
		return new DecimalFormat(format).format(a); 
	}

	@Override
	public List<String> nextSequence(String key, String format, int count) {
		if(count<=0){
			throw new IllegalArgumentException("参数count不能小于0");
		}
		Integer a = null;
		synchronized (key.intern()) {
			try {
				a = p.get(key);
				if(a==null){
					a = count;
					p.put(key, a);
				}else{
					a = a+count;
					p.put(key, a);
				}
				FileOutputStream out = null;
				try {
					out = new FileOutputStream(file);
					out.write(JsonHelper.toJson(p).getBytes("utf-8"));
				} catch (FileNotFoundException e) { 
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}finally{
				 
			} 
		}
		DecimalFormat df= new DecimalFormat(format);
		List<String> re=new ArrayList<String>();
		for(int i=count;i>0;i--){
			re.add(df.format(a-i+1));
		}
		return re;
	}
	private String readString(InputStream inputStream) throws IOException {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			StreamUtils.copy(inputStream, out);
			return new String(out.toByteArray(),"utf-8");
		}finally {
			if(inputStream!=null) {
				try {
					inputStream.close();
				} catch (Exception e) { 
				}
			}
		}
	}
	@Override
	public int nextIntSeq(String key) {
		// TODO Auto-generated method stub
		return 0;
	}
}

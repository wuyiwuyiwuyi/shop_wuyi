package com.qf.back_manager;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "com.qf")
@Import(FdfsClientConfig.class)
public class BackManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackManagerApplication.class, args);
	}

}

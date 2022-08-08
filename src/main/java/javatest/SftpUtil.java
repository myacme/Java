//package javatest;
//
//
//import com.jcraft.jsch.*;
//import lab.captain.common.util.StringUtils;
//import lab.captain.pwy.entity.SftpConfig;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.annotation.Resource;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Vector;
//
///**
// * @author ljx
// * @version 1.0.0
// * @create 2022/8/5 15:32
// */
//@Component
//public class SftpUtil {
//
//	@Resource
//	private SftpConfig sftpConfig;
//
//	private static final Logger logger = LoggerFactory.getLogger(SftpUtil.class);
//
//	private ChannelSftp channelSftp;
//
//	private Session session;
//
//	/**
//	 * 连接sftp服务器
//	 */
//	public void connect() throws Exception {
//		if (session == null || !session.isConnected() || channelSftp == null || !channelSftp.isConnected()) {
//			Integer port = sftpConfig.getPort();
//			String name = sftpConfig.getHostname();
//			String host = sftpConfig.getHost();
//			JSch jSch = new JSch();
//			logger.info("连接ftp主机，ip：{}:{}", sftpConfig.getHost(), sftpConfig.getPort());
//			try {
//				if (port <= 0) {
//					// 连接服务器，默认端口
//					session = jSch.getSession(name, host);
//				} else {
//					session = jSch.getSession(name, host, port);
//				}
//				if (session == null) {
//					throw new Exception("session is null");
//				}
//				// 设置登录主机的密码
//				session.setPassword(sftpConfig.getPassword());
//				// 设置第一次登录的时候提示,不检查密钥 可选值：(ask | yes | no)
//				session.setConfig("StrictHostKeyChecking", "no");
//				// 设置登录超时时间
//				session.connect(sftpConfig.getTimeout());
//				// 创建sftp通信通道
//				Channel channel = session.openChannel("sftp");
//				channel.connect(5000);
//				channelSftp = (ChannelSftp) channel;
//				//进入默认目录
//				channelSftp.cd(sftpConfig.getPath());
//				logger.info("SFTP服务器登录成功！ip：{}:{}", sftpConfig.getHost(), sftpConfig.getPort());
//			} catch (Exception e) {
//				logger.info("SFTP服务器登录失败！ip：{}:{}", sftpConfig.getHost(), sftpConfig.getPort());
//				e.printStackTrace();
//				throw new Exception("SFTP服务器连接失败：{}", e);
//			}
//		}
//	}
//
//	/**
//	 * 关闭连接
//	 */
//	public void disConnect() {
//		if (session != null && session.isConnected()) {
//			session.disconnect();
//		}
//		if (channelSftp != null && channelSftp.isConnected()) {
//			channelSftp.disconnect();
//		}
//	}
//
//	/**
//	 * 批量上传文件
//	 */
//	public String batchUploadFile(String path, String fileName, MultipartFile[] files) throws Exception {
//		// 登录服务器
//		connect();
//		try {
//			if (!StringUtils.isEmpty(path)) {
//				path = sftpConfig.getPath() + "/" + path;
//				if (batchRemoveFile(path)) {
//					try {
//						channelSftp.cd(path);
//					} catch (SftpException e) {
//						try {
//							channelSftp.mkdir(path);
//							// 进入指定目录
//							channelSftp.cd(path);
//						} catch (SftpException e1) {
//							logger.info("SFTP创建文件路径失败!path:{}", path);
//							throw new RuntimeException("SFTP创建文件路径失败:", e1);
//						}
//					}
//					logger.info("SFTP上传路径：{}", path);
//				}
//				for (MultipartFile file : files) {
//					try (InputStream inputStream = new ByteArrayInputStream(file.getBytes())) {
//						System.out.println(file.getOriginalFilename());
//						channelSftp.put(inputStream, file.getOriginalFilename());
//					} catch (Exception e) {
//						logger.info("SFTP批量上传异常!");
//						throw new RuntimeException("SFTP批量上传异常:", e);
//					}
//				}
//			} else {
//				throw new RuntimeException("路径不能为空");
//			}
//			return path;
//		} catch (Exception e) {
//			logger.info("SFTP批量上传异常!");
//			throw new RuntimeException("SFTP批量上传异常:", e);
//		} finally {
//			disConnect();
//		}
//	}
//
//	/**
//	 * 上传zip文件
//	 *
//	 * @param path
//	 * @param fileName
//	 * @param files
//	 * @return
//	 *
//	 * @throws Exception
//	 */
//	public String uploadZipFile(String path, String fileName, MultipartFile[] files) throws Exception {
//		// 登录服务器
//		connect();
//		try {
//			if (!StringUtils.isEmpty(path)) {
//				path = sftpConfig.getPath() + "/" + path;
//				try {
//					channelSftp.cd(path);
//				} catch (SftpException e) {
//					try {
//						channelSftp.mkdir(path);
//						// 进入指定目录
//						channelSftp.cd(path);
//					} catch (SftpException e1) {
//						logger.info("SFTP创建文件路径失败!path:{}", path);
//						throw new RuntimeException("SFTP创建文件路径失败:", e1);
//					}
//				}
//				logger.info("SFTP上传路径：{}", path);
//				try (InputStream inputStream = ZipUtil.zip(files, fileName)) {
//					channelSftp.put(inputStream, fileName);
//				} catch (Exception e) {
//					logger.info("SFTP批量上传异常!");
//					throw new RuntimeException("SFTP批量上传异常:", e);
//				}
//			} else {
//				throw new RuntimeException("路径不能为空");
//			}
//			return path;
//		} catch (Exception e) {
//			logger.info("SFTP批量上传异常!");
//			throw new RuntimeException("SFTP批量上传异常:", e);
//		} finally {
//			disConnect();
//		}
//	}
//
//	/**
//	 * 批量下载文件
//	 *
//	 * @param path 下载路径
//	 */
//	public List<OutputStream> batchDownloadFile(String path) throws Exception {
//		// 登录服务器
//		connect();
//		List<OutputStream> list = new ArrayList<>();
//		try {
//			if (!StringUtils.isEmpty(path)) {
//				path = sftpConfig.getPath() + "/" + path;
//				if (verifyDirectoryExists(path)) {
//					Vector<ChannelSftp.LsEntry> files = channelSftp.ls(path);
//					channelSftp.cd(path);
//					for (ChannelSftp.LsEntry file : files) {
//						String filename = file.getFilename();
//						if (!filename.startsWith(".")) {
//							try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {
//								channelSftp.get(filename, outputStream);
//								list.add(outputStream);
//							}
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			logger.info("SFTP批量下载失败!异常：{}", e.getMessage());
//			throw new RuntimeException("SFTP批量上传异常：", e);
//		} finally {
//			// 关闭连接
//			disConnect();
//		}
//		return list;
//	}
//
//	/**
//	 * 下载文件
//	 *
//	 * @param path 下载路径
//	 */
//	public ByteArrayOutputStream downloadFile(String path, String fileName) throws Exception {
//		// 登录服务器
//		connect();
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//		try {
//			if (!StringUtils.isEmpty(path)) {
//				path = sftpConfig.getPath() + "/" + path;
//				if (verifyDirectoryExists(path)) {
//					channelSftp.cd(path);
//					channelSftp.get(fileName, outputStream);
//				}
//			}
//		} catch (
//				Exception e) {
//			logger.info("SFTP下载失败!异常：{}", e.getMessage());
//			throw new RuntimeException("SFTP下载异常：", e);
//		} finally {
//			// 关闭连接
//			disConnect();
//		}
//		return outputStream;
//	}
//
//	/**
//	 * 批量删除文件
//	 *
//	 * @param path 路径
//	 */
//	public boolean batchRemoveFile(String path) {
//		try {
//			if (verifyDirectoryExists(path)) {
//				Vector<ChannelSftp.LsEntry> files = channelSftp.ls(path);
//				if (files.size() > 0) {
//					channelSftp.cd(path);
//					for (ChannelSftp.LsEntry file : files) {
//						String filename = file.getFilename();
//						if (!filename.startsWith(".")) {
//							channelSftp.rm(filename);
//						}
//					}
//				}
//			}
//			return true;
//		} catch (Exception e) {
//			logger.info("SFTP删除失败!异常：{}", e.getMessage());
//			throw new RuntimeException("SFTP删除异常：", e);
//		}
//	}
//
//	/**
//	 * 验证目录是否存在
//	 *
//	 * @param path
//	 * @return
//	 */
//	public boolean verifyDirectoryExists(String path) {
//		try {
//			channelSftp.cd(path);
//			channelSftp.cd(sftpConfig.getPath());
//			return true;
//		} catch (SftpException e) {
//			logger.info(" {} 路径不存在", path);
//			return false;
//		}
//	}
//
//	/**
//	 * 文件流下载
//	 *
//	 * @param targetPath   目标服务器上的文件目录
//	 * @param sftpFileName 目标服务器文件名
//	 * @author ljx
//	 */
//	public ByteArrayOutputStream downloadFileStream(String targetPath, String sftpFileName) throws Exception {
//		// 登录服务器
//		connect();
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//		try {
//			channelSftp.get(targetPath + "/" + sftpFileName, outputStream);
//			return outputStream;
//		} catch (SftpException e) {
//			e.printStackTrace();
//			return null;
//		} finally {
//			disConnect();
//		}
//	}
//}
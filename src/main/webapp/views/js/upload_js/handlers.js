function uploadSuccess(file, serverData) {
	if (serverData == null || serverData == '') {
		$("#" + this.customSettings.showSpan).next("b").html(
				'<img src="' + ctx + 'img/fail.gif"/>');
	} else {
		$("#" + this.customSettings.hiddenInput).val(serverData);
		$("#" + this.customSettings.showSpan).next("b").html(
				'<img src="' + ctx + 'img/done.gif"/>');

	}

}
function fileDialogComplete(numFilesSelected, numFilesQueued) {
	if (numFilesSelected > 0) {
		document.getElementById(this.customSettings.startButtonId).disabled = true;
		this.startUpload();
	}
}

function uploadStart(file) {
	var size = file.size;

	var unit = "B";

	if (size > (1024 * 1024 * 1024)) {

		unit = "GB";

		size /= (1024 * 1024 * 1024);

	} else if (size > (1024 * 1021)) {

		unit = "MB";

		size /= (1024 * 1024);

	} else if (size > 1024) {

		unit = "KB";

		size /= 1024;

	}
	var name = file.name.length > 12 ? file.name.substring(0,12)+"..." : file.name;
	$("#" + this.customSettings.showSpan).html(name + " " + size.toFixed(2) + "" + unit);
	$("#" + this.customSettings.showSpan).attr("title",file.name);
	$("#" + this.customSettings.hiddenInput).val("");
	return true;
}

function uploadProgress(file, bytesLoaded, bytesTotal) {
	var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);
	$("#" + this.customSettings.showSpan).next("b").html(percent + "%");
}



function uploadError(file, errorCode, message) {
		switch (errorCode) {
		case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
			$("#" + this.customSettings.showSpan).next("b").html("上传错误: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
			$("#" + this.customSettings.showSpan).next("b").html("上传失败.");
			break;
		case SWFUpload.UPLOAD_ERROR.IO_ERROR:
			$("#" + this.customSettings.showSpan).next("b").html("服务 (IO)错误");
			break;
		case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
			$("#" + this.customSettings.showSpan).next("b").html("Security Error");
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
			$("#" + this.customSettings.showSpan).next("b").html("Upload limit exceeded.");
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
			$("#" + this.customSettings.showSpan).next("b").html("验证失败.  略过上传.");
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
			// If there aren't any files left (they were all cancelled) disable the cancel button
			if (this.getStats().files_queued === 0) {
				document.getElementById(this.customSettings.startButtonId).disabled =true;
			}
			$("#" + this.customSettings.showSpan).next("b").html("取消了上传");
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
			$("#" + this.customSettings.showSpan).next("b").html("停止");
			break;
		default:
			$("#" + this.customSettings.showSpan).next("b").html("Unhandled Error: " + errorCode);
			break;
		}
}

function fileQueueError(file, errorCode, message) {
		switch (errorCode) {
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			$.messager.alert("操作提示", "文件过大!", "info");
			break;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			$.messager.alert("操作提示", "文件为空!", "info");
			break;
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
			$.messager.alert("操作提示", "不可用的文件类型!", "info");
			break;
		default:
			$.messager.alert("操作提示", message, "info");
			break;
		}
}



function initSWFUpload(spanButtonPlaceHolder,startButton,showspan,hiddeninput,url) {
	new SWFUpload(
			{
				flash_url : ctx + "js/upload_js/swfupload.swf",
				upload_url : url,
				file_size_limit : "1MB",
				file_types : "*.JPG;*.GIF;*.PNG,*.bmp", // 设置文件格式
				file_types_description : "图片文件",
				file_upload_limit : 500,
				file_queue_limit : 1,
				file_post_name : "attachment",// 加了这个，在webwork的action中要用
				custom_settings : {
					startButtonId : startButton,
					showSpan : showspan,
					hiddenInput : hiddeninput
				},
				debug : false,

				// Button settings
				button_image_url : ctx + "img/ssbt_bg.png",
				button_width : "90",
				button_height : "27",
				button_placeholder_id : spanButtonPlaceHolder,
				button_text : '<span class="theFont">上传文件</span>',
				button_text_style : ".theFont {color: #FFFFFF; cursor:pointer; text-align: center; height: 24px; font: 12px '宋体';}",
				button_text_left_padding : 0,
				button_text_top_padding : 5,
				button_window_mode: 'transparent',

				file_dialog_complete_handler : fileDialogComplete, // 选择好文件自动提交
				file_queue_error_handler : fileQueueError,
				upload_start_handler : uploadStart,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess
			});
}

function initSWFUploadOther(spanButtonPlaceHolder,startButton,showspan,hiddeninput,url) {
	new SWFUpload(
			{
				flash_url : ctx + "js/upload_js/swfupload.swf",
				upload_url : url,
				file_size_limit : "15MB",
				file_types : "*.JPG;*.GIF;*.PNG;*.ZIP;*.RAR", // 设置文件格式
				file_types_description : "压缩文件",
				file_upload_limit : 500,
				file_queue_limit : 1,
				file_post_name : "attachment",// 加了这个，在webwork的action中要用
				custom_settings : {
					startButtonId : startButton,
					showSpan : showspan,
					hiddenInput : hiddeninput
				},
				debug : false,

				// Button settings
				button_image_url : ctx + "img/ssbt_bg.png",
				button_width : "90",
				button_height : "27",
				button_placeholder_id : spanButtonPlaceHolder,
				button_text : '<span class="theFont">上传文件</span>',
				button_text_style : ".theFont {color: #FFFFFF; cursor:pointer; text-align: center; height: 24px; font: 12px '宋体';}",
				button_text_left_padding : 0,
				button_text_top_padding : 5,
				button_window_mode: 'transparent',
				

				file_dialog_complete_handler : fileDialogComplete, // 选择好文件自动提交
				file_queue_error_handler : fileQueueError,
				upload_start_handler : uploadStart,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess
			});
}

function initSWFUploadcomPress(spanButtonPlaceHolder,startButton,showspan,hiddeninput,url) {
	new SWFUpload(
			{
				flash_url : ctx + "js/upload_js/swfupload.swf",
				upload_url : url,
				file_size_limit : "15MB",
				file_types : "*.ZIP;*.RAR", // 设置文件格式
				file_types_description : "自定义文件",
				file_upload_limit : 500,
				file_queue_limit : 1,
				file_post_name : "attachment",// 加了这个，在webwork的action中要用
				custom_settings : {
					startButtonId : startButton,
					showSpan : showspan,
					hiddenInput : hiddeninput
				},
				debug : false,

				// Button settings
				button_image_url : ctx + "img/ssbt_bg.png",
				button_width : "90",
				button_height : "27",
				button_placeholder_id : spanButtonPlaceHolder,
				button_text : '<span class="theFont">上传文件</span>',
				button_text_style : ".theFont {color: #FFFFFF; cursor:pointer; text-align: center; height: 24px; font: 12px '宋体';}",
				button_text_left_padding : 0,
				button_text_top_padding : 5,
				button_window_mode: 'transparent',

				file_dialog_complete_handler : fileDialogComplete, // 选择好文件自动提交
				file_queue_error_handler : fileQueueError,
				upload_start_handler : uploadStart,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess
			});
}

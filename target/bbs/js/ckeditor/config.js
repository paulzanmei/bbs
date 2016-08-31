/**
 * @license Copyright (c) 2003-2016, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.enterMode = CKEDITOR.ENTER_BR;
	config.shiftEnterMode = CKEDITOR.ENTER_P;
	filebrowserBrowseUrl = '/ckfinder2.3.1/ckfinder.html';
    filebrowserImageBrowseUrl = '/ckfinder2.3.1/ckfinder.html?type=Images';
    filebrowserFlashBrowseUrl = '/ckfinder2.3.1/ckfinder.html?type=Flash';
    filebrowserUploadUrl = '/ckfinder2.3.1/core/connector/java/connector.java?command=QuickUpload&type=Files';
    filebrowserImageUploadUrl = '/ckfinder2.3.1/core/connector/java/connector.java?command=QuickUpload&type=Images';
    filebrowserFlashUploadUrl = '/ckfinder2.3.1/core/connector/java/connector.java?command=QuickUpload&type=Flash';
	config.filebrowserImageUploadUrl = 'upload.php?type=img';
	config.image_previewText='';
	config.language = 'zh-cn';
};

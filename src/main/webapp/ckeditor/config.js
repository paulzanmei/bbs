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
	config.filebrowserBrowseUrl = '/bbs/ckfinder';
	config.filebrowserImageBrowseUrl = '/bbs/ckfinder?type=Images';
    config.filebrowserFlashBrowseUrl = '/bbs/ckfinder?type=Flash';
    config.filebrowserUploadUrl = '/bbs/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';
    config.filebrowserImageUploadUrl = '/bbs/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';
    config.filebrowserFlashUploadUrl = '/bbs/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';
	config.image_previewText='';
	config.language = 'zh-cn';
};

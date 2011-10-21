
Ext.ns( "M55", "M55.kui", "M55.json" );

/*
 * kui configuration.
 */
M55.kui.version = 0.5;
M55.kui.versionUrl = 'http://www.marker55.com/kui/versions.json';
M55.kui.about = '<center><h1>kui - ' + M55.kui.version + '</h1>' + '<br/>' +
	'<a href="http://www.marker55.com/kui/" target="_blank">http://www.marker55.com/kui/</a></center>';

/*
 * ExtJS configuration.
 */
Ext.BLANK_IMAGE_URL = "library/javascript/ext-3.0.3/resources/images/default/s.gif"; 
Ext.USE_NATIVE_JSON = true;

Ext.Ajax.disableCaching = true;

// Mask does not go away in Opera.
if ( !Ext.isOpera ) {
    M55.mask = new Ext.LoadMask( Ext.getBody(), { msg: "Loading..." } );
    
    Ext.Ajax.on( 'beforerequest', M55.mask.show, M55.mask );
    Ext.Ajax.on( 'requestcomplete', M55.mask.hide, M55.mask );
    Ext.Ajax.on( 'requestexception', M55.mask.hide, M55.mask );
}

Ext.Msg.minWidth = 200;

/*
 * Common methods.
 */
M55.info = function( message ) {
    Ext.Msg.alert( 'Info', message );
};

M55.error = function( message ) {
    Ext.Msg.alert( 'Error', message );
};

M55.errorResponse = function( response ) {
    var object = M55.json.decode( response.responseText );
    var message = "Unknown error.";
    if ( object ) {
        message = object.description;
    }
    Ext.Msg.alert( 'Error', message );
};

M55.json.decode = function( string ) {
	return Ext.util.JSON.decode( string );
};


var treePanel = new Ext.tree.TreePanel( {
    id: M55.ui.treePanel,
    region: 'west',

    width: 200,
    margins: '5 0 0 5',
    buttonAlign: 'left',

    title: 'MBeans',

    animate: false,
    autoScroll: true,
    useArrows: true,
    
    loader: new Ext.tree.TreeLoader( {
        dataUrl: 'mbeans.json',
        requestMethod: 'GET'
    } ),
    
    root: {
        nodeType: 'async',
        text: 'MBeans',
        draggable: false,
        expanded: true
    },
    
    listeners: {
        click: function( node ) {
            if ( node.isLeaf() ) {
                M55.getMBeanTab( node.attributes.id );
            }
        }
    },
    
    tbar: [ 
        {
            iconCls: 'icon-refresh',
            handler: function() {
                Ext.getCmp( M55.ui.treePanel ).getRootNode().reload();
            }
        },
        '->',
        {
            iconCls: 'icon-expand-all',
            handler: function() {
                Ext.getCmp( M55.ui.treePanel ).getRootNode().expandChildNodes( true );
            }
        },
        {
            iconCls: 'icon-collapse-all',
            handler: function() {
                Ext.getCmp( M55.ui.treePanel ).getRootNode().collapseChildNodes( true );
            }
        }
    ]
    
} );

var mainPanel = new Ext.TabPanel( {
    id: M55.ui.mainPanel,
    region: 'center',
    margins: '5 5 0 0',
    
    minTabWidth: 135,
    tabWidth: 135,
    resizeTabs: true,
    activeTab: 0,

    items: [ 
        {
            title: 'Home',
            frame: true,
            border: false,
            html: 'Select an MBean from the tree on the left.'
        }    
    ],
    
    enableTabScroll: true

} );

var aboutWindow = new Ext.Window( {
	
	id: 'about',
	title: 'About',
	
    closable: true,
    closeAction: 'hide',
    resizable: false,
    modal: true,
	
	items: [
		new Ext.Panel( {
		    layout: 'fit',
		    //border: false,
		    frame: true,

	        html: M55.kui.about

		} )
    ]
	
} );

var bbarPanel = new Ext.Panel( {
	region: 'south',
	html: '',
    bbar: [
        '->',
        {
            iconCls: 'icon-help',
            menu: new Ext.menu.Menu( {
                items: [
                    {
                        text: 'Check for Upgrade',
                        handler: function() {
                            M55.showUpgradeWindow();
                        }
                    },
                    '-',
                    {
                        text: 'About',
                        handler: function() {
                            aboutWindow.show();
                        }
                    }
                ]
            } )
        }
    ]
} );

var loginForm = new Ext.FormPanel( {
    
    id: M55.login.loginFormId,

    border: false,
    frame: true,
    defaultType: 'textfield',
    
    items: [
        {
            id: M55.login.usernameId,
            fieldLabel: 'Username',
            name: 'username'
        },
        {
            id: M55.login.passwordId,
            inputType: 'password',
            fieldLabel: 'Password',
            name: 'password'
        }        
    ]

} );

var loginWindow = new Ext.Window( {

    title: 'Login',
    closable: false,
    constrain: true,
    constrainHeader: true,
    
    width: 275,
    height: 135,
    
    items: [ loginForm ],
    
    buttons: [
        {
            id: M55.login.loginBtn,
            text: 'Login',
            handler: M55.login.authenticate
        },
        {
            text: 'Reset',
            handler: M55.login.reset
        }
    ]
    
} );

var versionUrl = M55.kui.versionUrl + '?version=' + M55.kui.version;

var scriptTagProxy = new Ext.data.ScriptTagProxy( {
    url: versionUrl
} );

var store = new Ext.data.JsonStore( {

    // store configs
    autoLoad: false,
    autoDestroy: true,
    proxy: scriptTagProxy,

    // reader configs
    root: 'versions',
    idProperty: 'version',
    fields: [ 'version', 'description' ]

} );

M55.showUpgradeWindow = function() {
    
    store.load( { callback: M55.showUpgradeWindowSuccess } );

};

M55.showUpgradeWindowSuccess = function() {
    
    store.sort( 'version', 'DESC' );
    
    var versionObject = store.getAt( 0 );
    
    var message = "No upgrade available.";
    
    if ( !versionObject ) {
        message = "Error while checking for upgrade.";
    }
    else if ( M55.kui.version < versionObject.data.version ) {
        message = "Version " + versionObject.data.version + " is available!";
    }
    
    var html = '<center><h1>' + message + '</h1><br/>' +
	'<a href="http://www.marker55.com/kui/" target="_blank">http://www.marker55.com/kui/</a></center>';

    var upgradeWindow = new Ext.Window( {
	
        title: 'Upgrade',

        closable: true,
        closeAction: 'close',
        resizable: false,
        modal: true,

        items: [
            new Ext.Panel( {
                layout: 'fit',

                frame: true,

                html: html

            } )
        ]

    } );

    store.removeAll();

    upgradeWindow.show();

};

M55.showLogin = function() {

	loginWindow.show();

};


M55.showApplication = function() {

	var viewport = new Ext.Viewport( {

	    layout: 'border',

	    defaults: {
	        collapsible: false,
	        split: true
	    },

	    items: [
	        treePanel,
	        mainPanel,
	        bbarPanel
        ]
	    

	} );
    
    viewport.doLayout();

};

Ext.onReady( function() {    
    
    M55.config( M55.login.isAuthenticationRequired );

} );

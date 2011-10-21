
Ext.ns( "M55.config", "M55.ui", "M55.login" );

M55.login.formId = 'loginForm';
M55.login.usernameId = 'loginForm.username';
M55.login.passwordId = 'loginForm.password';
M55.login.loginBtn = 'loginForm.loginBtn';

M55.ui.mainPanel = 'main-panel';
M55.ui.treePanel = 'tree-panel';

M55.config = function( callback ) {

    Ext.Ajax.request( {
        method: 'GET',
        url: 'configuration.json',
        success: callback,
        failure: M55.errorResponse
    } );

};

M55.login.isAuthenticationRequired = function( response ) {

    var message = M55.json.decode( response.responseText );
    if ( message.outcome === "ERROR" ) {
        M55.error( message.description );
        return;
    }
    
    if ( message.value.authenticationRequired ) {
        M55.showLogin();
        return;
    }

	M55.showApplication();

};

M55.login.authenticate = function( button ) {

    var username = Ext.getCmp( M55.login.usernameId ).getValue();
    var password = Ext.getCmp( M55.login.passwordId ).getValue();
    
    Ext.Ajax.request( {
        method: 'POST',
        url: 'user.json',
        params: { 
            username: username,
            password: password
        },
        success: M55.login.authenticateSuccess,
        failure: M55.errorResponse
    } );

};

M55.login.authenticateSuccess = function( response ) {

    var message = M55.json.decode( response.responseText );

    if ( message.outcome === "ERROR" || !message.value.authenticated ) {
        M55.error( message.description );
        return;
    }
    
    loginWindow.hide();
    M55.showApplication();

    //document.location.href = "#showApplication";

};

M55.login.reset = function( button ) {

    Ext.getCmp( M55.login.usernameId ).reset();
    Ext.getCmp( M55.login.passwordId ).reset();

};

M55.getMBeanTab = function( mbean ) {
    var existingTab = Ext.getCmp( M55.ui.mainPanel ).findById( mbean);
    if ( existingTab ) {
        existingTab.show();
    }
    else {
        Ext.Ajax.request( {
            method: 'GET',
            url: 'mbeans.json',
            params: { id: mbean },
            success: M55.loadMBeanTab,
            failure: M55.errorResponse
        } );
    }
};

M55.setAttribute = function( button ) {
    var buttonSplit = button.getId().split('#');
    var mbeanName = buttonSplit[0];
    var attributeName = buttonSplit[1];
    
    var attributeValue = Ext.getCmp( mbeanName + '#' + attributeName ).getValue();
    
    Ext.Ajax.request( {
        method: 'POST',
        url: 'attribute.json',
        params: { 
            mbeanName: mbeanName,
            attributeName: attributeName,
            attributeValue: attributeValue
        },
        success: M55.setAttributeSuccess,
        failure: M55.errorResponse
    } );
};

M55.setAttributeSuccess = function( response ) {
    var message = M55.json.decode( response.responseText );
    var fn = null;
    if ( message.outcome === "ERROR" ) {
        fn = M55.error;
    }
    else {
        fn = M55.info;
    }
    fn( message.description );
};

M55.invokeOperation = function( button ) {
    var buttonSplit = button.getId().split('#');
    var mbeanName = buttonSplit[0];
    var operationName = buttonSplit[1];
    
    var paramString = "";
    paramString += "mbeanName=" + escape( mbeanName );
    paramString += "&";
    paramString += "operationName=" + escape( operationName );

    var i = 0;

    do {
        var paramName = mbeanName + "#" + operationName + "#parameter[" + i + "]";
        var paramField = Ext.getCmp( paramName );
        
        if ( !paramField ) {
            break;
        }

        paramString += "&";            
        paramString += escape( "parameter[" + i + "]" ) + "=" + escape( paramField.getValue() );
        
        i++;
        
    } while( true );
    
    Ext.Ajax.request( {
        method: 'POST',
        url: 'operation.json',
        params: paramString,
        success: M55.invokeOperationSuccess,
        failure: M55.errorResponse
    } );
};

M55.invokeOperationSuccess = function( response ) {
    var message = M55.json.decode( response.responseText );
    if ( message.outcome === "ERROR" ) {
        M55.error( message.description );
        return;
    }
    
    M55.info( "Operation invoked successfully.  Returned [" + message.value + "]" );        

};

M55.loadMBeanTab = function( response ) {
    var mbean = M55.json.decode( response.responseText );
    
    var infoTable = new Ext.Panel( {
        title: 'MBeanInfo',
        layout: 'table',
        
        layoutConfig: {
            columns: 2
        },
        
        style: {
            marginBottom: '6px'
        },
        
        titleCollapse: true,
        collapsible: true,
        frame: true,
        
        items: [
            {
                style: {
                    fontWeight: 'bold'
                },            
                html: '<p> Name </p>'
            },
            {
                style: {
                    fontWeight: 'bold'
                },            
                html: '<p> Value </p>'
            },
            {
                style: {
                    marginRight: '6px'
                },            
                html: '<p> ObjectName </p>'
            },
            new Ext.Panel ( { 
                items: [ 
                     new Ext.form.TextField ( {
                        grow: true,
                        growMin: 150,
                        growMax: 600,
                        readOnly: true,
                        hideLabel: true,
                        value: mbean.name
                     } )
                 ]
            } ),
            {
                style: {
                    marginRight: '6px'
                },            
                html: '<p> ClassName </p>'
            },
            new Ext.Panel ( { 
                items: [ 
                    new Ext.form.TextField ( {
                        grow: true,
                        growMin: 150,
                        growMax: 600,
                        readOnly: true,
                        hideLabel: true,
                        value: mbean.className
                    } )
                ]
            } ),
            {
                style: {
                    marginRight: '6px'
                },            
                html: '<p> Description </p>'
            },
            new Ext.Panel ( { 
                items: [ 
                    new Ext.form.TextField ( {
                        grow: true,
                        growMin: 150,
                        growMax: 600,
                        readOnly: true,
                        hideLabel: true,
                        value: mbean.description
                    } )
                ]
            } )
        ]
    } );
    
    var attributeItems = [];
    
    attributeItems.push( {
        style: {
            fontWeight: 'bold'
        },            
        html: '<p> Attribute </p>'          
    } );
    attributeItems.push( {
        style: {
            fontWeight: 'bold'
        },            
        html: '<p> Value </p>'          
    } );
    attributeItems.push ( {
        style: {
            fontWeight: 'bold'
        },            
        html: '<p> Action </p'
    } );
    for ( var i=0; i<mbean.attributes.length; i++ ) {
        var attribute = mbean.attributes[i];
        attributeItems.push( {
            style: {
                marginRight: '6px'
            },            
            html: '<p> ' + attribute.name + ' </p>'
        } );
        attributeItems.push(
            new Ext.Panel ( { 
                items: [ 
                    new Ext.form.TextField ( {
                        id: mbean.name + "#" + attribute.name,
                        style: {
                            marginRight: '6px'
                        },            
                        grow: true,
                        growMin: 150,
                        growMax: 600,
                        hideLabel: true,
                        readOnly: !attribute.writable || !attribute.available,
                        value: attribute.available ? attribute.value : "[Unavailable]"
                    } )
                ]
            } ) 
        );
        if ( attribute.writable ) {
            attributeItems.push(
                new Ext.Panel ( { 
                    items: [ 
                        new Ext.Button( {
                            id: mbean.name + "#" + attribute.name + '#button',
                            text: 'Set',
                            handler: M55.setAttribute
                        } )
                    ]
                } )
            );
        }
        else {
            attributeItems.push ( {
                html: '<p></p>'
            } );
        }
    }
    
    var attributeTable = new Ext.Panel( {
        title: 'Attributes',
        layout: 'table',
                    
        layoutConfig: {
            columns: 3
        },
        
        style: {
            marginBottom: '6px'
        },
        
        titleCollapse: true,
        collapsible: true,
        frame: true,
        
        items: attributeItems
    } );
    
    var operationItems = [];
    operationItems.push( {
        style: {
            fontWeight: 'bold'
        },            
        html: '<p> Operation </p>'          
    } );
    operationItems.push( {
        style: {
        fontWeight: 'bold'
    },            
        html: '<p> Parameter(s) </p>'
    } );
    operationItems.push ( {
        style: {
            fontWeight: 'bold'
        },            
        html: '<p> Action </p'
    } );

    for ( i=0; i<mbean.operations.length; i++ ) {
        var operation = mbean.operations[i];

        var firstRow = true;
        
        var j = 0;
        do {
            var parameter = operation.parameters[j];

            if ( firstRow ) {
                operationItems.push( {
                    style: {
                        marginRight: '6px'
                    },            
                    html: '<p> ' + operation.name + ' </p>'
                } );                    
            }
            else {
                operationItems.push( {
                    html: '<p> </p>'
                } );
            }
            
            if ( parameter ) {
                operationItems.push( new Ext.Panel ( { 
                    items: [ 
                        new Ext.form.TextField ( {
                            id: mbean.name + "#" + operation.name + '#parameter[' + j + ']',
                            style: {
                                marginRight: '6px'
                            },            
                            hideLabel: true,
                            emptyText: parameter
                        } )
                    ]
                } ) );
            }
            else {
                operationItems.push( {
                    html: '<p> </p>'
                } );
            }
            
            if ( firstRow ) {
                operationItems.push( new Ext.Panel ( { 
                    items: [ 
                        new Ext.Button( {
                        id: mbean.name + "#" + operation.name + '#button',
                        text: 'Invoke',
                        handler: M55.invokeOperation
                        } )
                    ]
                } ) );
            }
            else {
                operationItems.push( {
                    html: '<p> </p>'
                } );
            }
            
            firstRow = false;                
            j++;
        }
        while ( operation.parameters[j] );

    }
    
    var operationTable = new Ext.Panel( {
        title: 'Operations',
        layout: 'table',
        
        layoutConfig: {
            columns: 3
        },
        
        style: {
            marginBottom: '6px'
        },
        
        titleCollapse: true,
        collapsible: true,
        frame: true,
        
        items: operationItems
    } );
    
    var newTab = new Ext.Panel( {
        id: mbean.name,
        title: mbean.displayName,
        
        labelWidth: 200,

        frame: true,
        border: false,
        closable: true,
        autoScroll: true,

        items: [
            infoTable,
            attributeTable,
            operationTable
        ]
    } );
    
    Ext.getCmp( M55.ui.mainPanel ).add( newTab ).show();
    
    //document.location.href = "#mbean=" + mbean.name;

};

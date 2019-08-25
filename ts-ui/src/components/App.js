import React from 'react';
import './App.scss';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import {connect} from 'react-redux';
import {MuiThemeProvider} from '@material-ui/core'
import {mainTheme, useStyles} from "../theme";
import MainTemplate from "../layout/MainTemplate";
import OAuth2RedirectHandler from "./user/oauth2/OAuth2RedirectHandler"

const App = ({open, toggleOpen, disclaimerAccepted, authenticated, location}) => {
    const classes = useStyles();
    console.log(location);
    return (
        <BrowserRouter>
            <MuiThemeProvider theme={mainTheme}>
                <Switch>
                    <Route path="/oauth2/redirect" component={OAuth2RedirectHandler}/>
                    <Route component={MainTemplate}/>
                </Switch>
            </MuiThemeProvider>
        </BrowserRouter>
    );
};

const mapStateToProps = state => ({
    disclaimerAccepted: state.system.accepted,
    authenticated: state.auth.authenticated
});


export default connect(mapStateToProps)(App);

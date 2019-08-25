import React, { Component } from 'react';
import { ACCESS_TOKEN } from '../../../constants';
import { Redirect } from 'react-router-dom'
import {tokenReceived} from "../../../redux/AuthActions";
import {connect} from "react-redux";

class OAuth2RedirectHandler extends Component {
    getUrlParameter(name) {
        name = name.replace(/[[]/, '\\[').replace(/[\]]/, '\\]');
        var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');

        var results = regex.exec(this.props.location.search);
        return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
    };

    render() {        
        const token = this.getUrlParameter('token');
        const error = this.getUrlParameter('error');
        if(token) {
            console.log("Received the token. Processing it now.")
            localStorage.setItem(ACCESS_TOKEN, token);
            this.props.login(token);

            return <Redirect to={{
                pathname: "/profile",
                state: { from: this.props.location }
            }}/>; 
        } else {
            return <Redirect to={{
                pathname: "/login",
                state: { 
                    from: this.props.location,
                    error: error 
                }
            }}/>; 
        }
    }
}

const mapStateToProps = props => ({});

const mapDispatchToProps = dispatch => ({
    login: (token) => {dispatch(tokenReceived(token))}
});

export default connect(mapStateToProps, mapDispatchToProps)(OAuth2RedirectHandler);
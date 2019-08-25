import React from 'react';
import './Signup.css';
import {Link, Redirect} from 'react-router-dom'
import fbLogo from '../resources/fb-logo.png';
import googleLogo from '../resources/google-logo.png';
import githubLogo from '../resources/github-logo.png';
import {signUp} from "../../../redux/AuthActions";
import {connect} from 'react-redux'
import {FACEBOOK_AUTH_URL, GITHUB_AUTH_URL, GOOGLE_AUTH_URL} from "../../../constants";

const Signup = props => {
    if (props.authenticated) {
        return <Redirect
            to={{
                pathname: "/",
                state: {from: props.location}
            }}/>;
    }

    const inputChange = (event) => {
        props[event.target.name] = event.target.value
    };

    const handleSubmit = event => {
        event.preventDefault();

        let request = {
            name: props.name,
            email: props.email,
            password: props.password
        };
        props.signUp(request);
    };

    return (
        <div className="signup-container">
            <div className="signup-content">
                <h1 className="signup-title">Signup with SpringSocial</h1>
                <div className="social-signup">
                    <a className="btn btn-block social-btn google" href={GOOGLE_AUTH_URL}>
                        <img src={googleLogo} alt="Google"/> Sign up with Google</a>
                    <a className="btn btn-block social-btn facebook" href={FACEBOOK_AUTH_URL}>
                        <img src={fbLogo} alt="Facebook"/> Sign up with Facebook</a>
                    <a className="btn btn-block social-btn github" href={GITHUB_AUTH_URL}>
                        <img src={githubLogo} alt="Github"/> Sign up with Github</a>
                </div>
                <div className="or-separator">
                    <span className="or-text">OR</span>
                </div>
                <form onSubmit={handleSubmit}>
                    <div className="form-item">
                        <input type="text" name="name"
                               className="form-control" placeholder="Name"
                               value={props.name} onChange={inputChange} required/>
                    </div>
                    <div className="form-item">
                        <input type="email" name="email"
                               className="form-control" placeholder="Email"
                               value={props.email} onChange={inputChange} required/>
                    </div>
                    <div className="form-item">
                        <input type="password" name="password"
                               className="form-control" placeholder="Password"
                               value={props.password} onChange={inputChange} required/>
                    </div>
                    <div className="form-item">
                        <button type="submit" className="btn btn-block btn-primary">Sign Up</button>
                    </div>
                </form>
                <span className="login-link">Already have an account? <Link to="/login">Login!</Link></span>
            </div>
        </div>
    );
};

const mapStateToProps = state => ({
    authenticated: state.auth.authenticated,
    email: "",
    name: "",
    password: ""
});

const mapDispatchToProps = dispatch => ({
    signUp: request => {
        dispatch(signUp(request));
    }
});
export default connect(mapStateToProps, mapDispatchToProps)(Signup)
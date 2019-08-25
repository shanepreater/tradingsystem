import React from 'react';
import {Redirect, Link as RouterLink} from 'react-router-dom'
import fbLogo from '../resources/fb-logo.png';
import googleLogo from '../resources/google-logo.png';
import githubLogo from '../resources/github-logo.png';
import {connect} from "react-redux";
import {FACEBOOK_AUTH_URL, GITHUB_AUTH_URL, GOOGLE_AUTH_URL} from "../../../constants";
import {logIn} from "../../../redux/AuthActions";
import Title from "../../Title";
import {Divider, InputLabel, Link, makeStyles, TextField, Button, Typography} from "@material-ui/core";

const useStyles = makeStyles(theme => ({
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    socialLink: {
        display: "block"
    },
    textField: {
        marginLeft: theme.spacing(1),
        marginRight: theme.spacing(1),
        width: 200,
    },
}));

const Login = (props) => {
    const classes = useStyles();
    if (props.authenticated) {
        return <Redirect
            to={{
                pathname: "/",
                state: {from: props.location}
            }}/>;
    }
    const inputChange = event => {
        props[event.target.name] = event.target.value;
    };
    const handleSubmit = event => {
        event.preventDefault();
        const request = {
            email: props.email,
            password: props.password

        };
        props.performLogin(request);
    };

    return (
        <React.Fragment>
            <Title>Login to continue</Title>
            <Link href={GOOGLE_AUTH_URL}>
                <img src={googleLogo} alt="Google" height="30em"/> Log in with Google
            </Link><br/>
            <Link href={FACEBOOK_AUTH_URL}>
                <img src={fbLogo} alt="Facebook" height="30em"/> Log in with Facebook
            </Link><br/>
            <Link href={GITHUB_AUTH_URL}>
                <img src={githubLogo} alt="Github" height="30em"/> Log in with Github
            </Link><br/>
            <Divider variant="middle"/>
            <Title>Or</Title>

            <form onSubmit={handleSubmit}>
                <TextField type="email" placeholder="Email" label="Email" className={classes.textField} onChange={inputChange} required/>
                <TextField type="password" placeholder="Password" label="Password" className={classes.textField} onChange={inputChange}/>
                <Button variant="contained" color="secondary" type="submit">Submit</Button>
            </form>
            <Typography>New user? <Link component={RouterLink} to="/signup">Sign up!</Link> </Typography>
        </React.Fragment>
    );
};

const mapStateToProps = state => ({
    email: "",
    password: "",
    authenticated: state.auth.authenticated

});
const mapDispatchToProps = dispatch => ({
    performLogin: request => {
        dispatch(logIn(request));
    }
});

export default connect(mapStateToProps, mapDispatchToProps)(Login);

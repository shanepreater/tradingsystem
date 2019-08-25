import React from 'react'
import {connect} from 'react-redux'
import {disclaimerAccepted} from '../redux/ConfigActions'
import {Button, makeStyles} from "@material-ui/core";
import DoneOutlineIcon from '@material-ui/icons/DoneOutline';

const useStyles = makeStyles(theme => ({
    button: {
        margin: theme.spacing(1),
    },
    input: {
        display: 'none',
    },
}));

const Disclaimer = ({disclaimerText, disclaimerVersion, user, handleAccept}) => {
    const classes = useStyles();
    return (
        <div className={'disclaimer-container container'}>
            <h2>Disclaimer</h2>
            <div dangerouslySetInnerHTML={{__html:disclaimerText}}/>
            <Button color="secondary"
                    className={classes.button}
                    variant="contained"
                    onClick={() => {handleAccept(user, disclaimerVersion)}}>
                <DoneOutlineIcon />&nbsp;Accept
            </Button>
        </div>
    );
};

const mapStateToProps = (state) => {
    return {
        disclaimerVersion: state.config.disclaimerVersion,
        disclaimerText: state.config.disclaimer,
        user: state.auth.user
    }
};

const mapDispatchToProps = (dispatch) => {
    return {
        handleAccept: (user, version) => {
            dispatch(disclaimerAccepted(user, version))
        }
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(Disclaimer)
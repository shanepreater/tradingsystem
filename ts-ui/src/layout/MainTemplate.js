import React from 'react';
import {Link as RouterLink, Route, Switch} from "react-router-dom";
import {connect} from 'react-redux';
import {
    AppBar,
    Avatar,
    Badge,
    Container,
    CssBaseline,
    Divider,
    Drawer,
    IconButton,
    Link,
    List,
    Toolbar,
    Typography
} from '@material-ui/core'
import MenuIcon from '@material-ui/icons/Menu';
import NotificationsIcon from '@material-ui/icons/Notifications';
import Main from "../components/body/Main";
import SetupInstructions from "../components/body/SetupInstructions";
import {useStyles} from "../theme";
import clsx from "clsx";
import ChevronLeftIcon from "@material-ui/core/SvgIcon/SvgIcon";
import {mainListItems, secondaryListItems} from "../components/header/ListItems";
import {toggleOpen} from "../redux/SystemActions";
import Login from "../components/user/login/Login";
import Disclaimer from "../components/Disclaimer";
import PersonIcon from '@material-ui/icons/Person';

const AppTitle = ({classes}) => (
    <Link component={RouterLink} to="/" color="inherit">
        <Typography component="h1" variant="h6" color="inherit" noWrap className={classes.title}>
            Opportunity Watcher Improved
        </Typography>
    </Link>
);


const ProfileImage = ({user, classes}) => {
    console.log(user);
    if (user.data.imageUrl) {
        return (
            <Avatar src={user.data.imageUrl} alt={user.data.name} className={classes.avatar}/>
        );
    } else {
        return (
            <React.Fragment>
                <PersonIcon className={classes.avatar}/>{user.data.name}
            </React.Fragment>
        );
    }
};

const LoggedOutToolBar = ({open, toggleOpen, classes}) => {
    return (
        <Toolbar className={classes.toolbar}>
            <IconButton edge="start"
                        className={classes.menuButton}
                        color="inherit"
                        aria-label="menu"
                        onClick={toggleOpen}>
                <MenuIcon/>
            </IconButton>

            <Typography component="h1" variant="h6" color="inherit" noWrap className={classes.title}>
                <AppTitle classes={classes}/>
            </Typography>
            <Link to="/login" component={RouterLink} color="inherit" className={classes.rightMenuButton}>
                <Typography color="inherit">Sign in</Typography>
            </Link>
            <Link to="/signup" component={RouterLink} color="inherit">
                <Typography color="inherit">Sign up</Typography>
            </Link>
        </Toolbar>
    );
};

const LoggedInToolBar = ({open, user, toggleOpen, classes}) => {
    return (
        <Toolbar className={classes.toolbar}>
            <IconButton edge="start"
                        className={classes.menuButton}
                        color="inherit"
                        aria-label="menu"
                        onClick={toggleOpen}>
                <MenuIcon/>
            </IconButton>
            <Typography component="h1" variant="h6" color="inherit" noWrap className={classes.title}>
                <AppTitle classes={classes}/>
            </Typography>
            <Link to="/signout" component={RouterLink} color="inherit" className={classes.rightMenuButton}>
                <Typography color="inherit">Sign out</Typography>
            </Link>
            <Link to="/profile" component={RouterLink} color="inherit" className={classes.rightMenuButton}>
                <ProfileImage user={user} classes={classes}/>
            </Link>
            <IconButton color="inherit">
                <Badge badgeContent={4} color="secondary">
                    <NotificationsIcon/>
                </Badge>
            </IconButton>
        </Toolbar>
    );
};

const MyToolBar = ({open, authenticated, user, toggleOpen, classes}) => {
    return authenticated ? (<LoggedInToolBar open={open} user={user} toggleOpen={toggleOpen} classes={classes}/>) :
        (<LoggedOutToolBar open={open} toggleOpen={toggleOpen} classes={classes}/>);
};

const MainTemplate = ({open, toggleOpen, disclaimerAccepted, user, authenticated}) => {
    const classes = useStyles();
    const tb = authenticated ? LoggedInToolBar : LoggedOutToolBar;
    const resolveDrawerClass = () => {
      return authenticated ? classes.authenticatedDrawerPaper : classes.drawerPaperClose;
    };
    return (
        <div className={classes.root}>
            <CssBaseline/>
            <AppBar position="absolute" className={classes.appBar}>
                <MyToolBar open={open} toggleOpen={toggleOpen} user={user} authenticated={authenticated}
                           classes={classes}/>
            </AppBar>
            <Drawer
                variant="permanent"
                classes={{
                    paper: clsx(classes.drawerPaper, !open && classes.drawerPaperClose),
                }}
                open={open}>
                <div className={classes.toolbarIcon}>
                    <IconButton onClick={toggleOpen}>
                        <ChevronLeftIcon/>
                    </IconButton>
                </div>
                <Divider/>
                <List>{mainListItems(authenticated, classes)}</List>
                <Divider/>
                <List>{secondaryListItems}</List>
            </Drawer>
            <main className={classes.content}>
                <div className={classes.appBarSpacer}/>
                <Container maxWidth="lg" className={classes.container}>
                    <Switch>
                        <Route path="/" exact component={Main}/>
                        <Route path="/setup" component={SetupInstructions}/>
                        <Route path="/login" component={Login}/>
                        <Route path="/signup" render={(props) => (
                            <Login classes={classes}/>
                        )}/>
                        <Route path="/disclaimer" component={Disclaimer}/>
                    </Switch>
                </Container>
            </main>
        </div>
    );
};

const mapStateToProps = state => ({
    open: state.system.drawerOpen,
    disclaimerAccepted: state.system.accepted,
    user: state.auth.user,
    authenticated: state.auth.authenticated
});

const mapDispatchToProps = dispatch => ({
    toggleOpen: () => {
        dispatch(toggleOpen());
    }
});


export default connect(mapStateToProps, mapDispatchToProps)(MainTemplate);

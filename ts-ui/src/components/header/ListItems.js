import React from 'react';
import {ListItem, ListItemIcon, ListItemText, ListSubheader} from '@material-ui/core';
import {Link as RouterLink} from "react-router-dom";
import AssignmentIcon from '@material-ui/icons/Assignment';
import ShowChartIcon from '@material-ui/icons/ShowChart';
import AndroidIcon from '@material-ui/icons/Android';
import HelpIcon from '@material-ui/icons/Help';
import PaymentIcon from '@material-ui/icons/Payment';
import FreeBreakfastIcon from '@material-ui/icons/FreeBreakfast';
import WarningIcon from '@material-ui/icons/Warning';

export const mainListItems = (authenticated, classes) => (
    <div>
        <ListItem button
                  component={RouterLink}
                  to="/trades"
                  className={authenticated ? classes.drawerLinkActive : classes.drawerLinkInactive}>
            <ListItemIcon>
                <ShowChartIcon/>
            </ListItemIcon>
            <ListItemText primary="Dashboard"/>
        </ListItem>
        <ListItem button
                  component={RouterLink}
                  to="/autobot"
                  className={authenticated ? classes.drawerLinkActive : classes.drawerLinkInactive}>
            <ListItemIcon>
                <AndroidIcon/>
            </ListItemIcon>
            <ListItemText primary="Configure Bot"/>
        </ListItem>
        <ListItem button
                  component={RouterLink}
                  to="/subscriptions"
                  className={authenticated ? classes.drawerLinkActive : classes.drawerLinkInactive}>
            <ListItemIcon>
                <PaymentIcon/>
            </ListItemIcon>
            <ListItemText primary="Subscriptions"/>
        </ListItem>
        <ListItem button
                  component={RouterLink}
                  to="/setup">
            <ListItemIcon>
                <HelpIcon/>
            </ListItemIcon>
            <ListItemText primary="Setup help"/>
        </ListItem>
        <ListItem button
                  component={RouterLink}
                  to="/disclaimer">
            <ListItemIcon>
                <WarningIcon/>
            </ListItemIcon>
            <ListItemText primary="Disclaimer"/>
        </ListItem>
        <ListItem button
                  component={RouterLink}
                  to="/demo"
                  className={authenticated ? classes.drawerLinkInactive : classes.drawerLinkActive}>
            <ListItemIcon>
                <FreeBreakfastIcon/>
            </ListItemIcon>
            <ListItemText primary="Demo"/>
        </ListItem>
    </div>
);

export const secondaryListItems = (authenticated, classes) => (
    <div className={authenticated ? classes.drawerLinkActive : classes.drawerLinkInactive}>
        <ListSubheader inset>Saved reports</ListSubheader>
        <ListItem button>
            <ListItemIcon>
                <AssignmentIcon/>
            </ListItemIcon>
            <ListItemText primary="Current month"/>
        </ListItem>
        <ListItem button>
            <ListItemIcon>
                <AssignmentIcon/>
            </ListItemIcon>
            <ListItemText primary="Last quarter"/>
        </ListItem>
        <ListItem button>
            <ListItemIcon>
                <AssignmentIcon/>
            </ListItemIcon>
            <ListItemText primary="Year-end sale"/>
        </ListItem>
    </div>
);
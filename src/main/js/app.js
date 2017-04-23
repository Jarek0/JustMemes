import React from 'react';
import ReactDOM from 'react-dom';
import {Router, Route, IndexRoute, browserHistory} from "react-router";

import Layout from './components/Layout';
import MainPage from './components/MainPage'
import ShowMemPage from './components/ShowMemPage'
import WaitingRoom from './components/WaitingRoom'
import Auth from './components/Auth'
import AddMem from './components/AddMem'


ReactDOM.render(
    <Router history={browserHistory}>
        <Route path="/" component={Layout}>
            <IndexRoute component={MainPage}></IndexRoute>
            <Route exact path="waiting" name="waiting_room" component={WaitingRoom}></Route>
            <Route exact path="waiting/(:page)" name="waiting_room" component={WaitingRoom}></Route>
            <Route exact path="showMem/(:id)" name="show_mem" component={ShowMemPage}></Route>
            <Route exact path="login" name="login" component={Auth}></Route>
            <Route exact path="/registration/complete" name="registrationComplete" component={Auth}></Route>
            <Route exact path="register" name="register" component={Auth}></Route>
            <Route exact path="(:page)" name="main_page" component={MainPage}></Route>
            <Route exact path="/mem/add" name="add_mem" component={AddMem}></Route>
        </Route>
    </Router>
    ,document.getElementById('layout'));

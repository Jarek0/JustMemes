import React from 'react';
import ReactDOM from 'react-dom';
import {Router, Route, IndexRoute, browserHistory} from "react-router";

import Layout from './components/Layout';
import MainPage from './components/MainPage'
import ShowMemPage from './components/ShowMemPage'
import WaitingRoom from './components/WaitingRoom'
import Auth from './components/Auth'


ReactDOM.render(
    <Router history={browserHistory}>
        <Route path="/" component={Layout}>
            <IndexRoute component={MainPage}></IndexRoute>
            <Route path="waiting" name="waiting_room" component={WaitingRoom}></Route>
            <Route path="waiting/(:page)" name="waiting_room" component={WaitingRoom}></Route>
            <Route path="showMem/(:id)" name="show_mem" component={ShowMemPage}></Route>
            <Route path="login" name="login" component={Auth}></Route>
            <Route path="register" name="register" component={Auth}></Route>
            <Route path="register" name="login" component={Auth}></Route>
            <Route path="(:page)" name="main_page" component={MainPage}></Route>

        </Route>
    </Router>
    ,document.getElementById('layout'));

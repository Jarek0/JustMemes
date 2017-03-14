import React from 'react';
import ReactDOM from 'react-dom';
import {Router, Route, IndexRoute, browserHistory} from "react-router";

import Layout from './components/Layout';
import MainPage from './components/MainPage'
import WaitingRoom from './components/WaitingRoom'


ReactDOM.render(
    <Router history={browserHistory}>
        <Route path="/" component={Layout}>
            <IndexRoute component={MainPage}></IndexRoute>
            <Route path="waiting" name="waiting_room" component={WaitingRoom}></Route>
            <Route path="waiting/(:page)" name="waiting_room" component={WaitingRoom}></Route>
            <Route path="(:page)" name="main_page" component={MainPage}></Route>

        </Route>
    </Router>
    ,document.getElementById('layout'));

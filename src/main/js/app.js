import React from 'react';
import ReactDOM from 'react-dom';
import {Router, Route, IndexRoute, browserHistory} from "react-router";

import Layout from './components/Layout';
import MainPage from './components/MainPage'


ReactDOM.render(
    <Router history={browserHistory}>
        <Route path="/" component={Layout}>
            <IndexRoute component={MainPage}></IndexRoute>
            <Route path="(:page)" component={MainPage}></Route>
        </Route>
    </Router>
    ,document.getElementById('layout'));

import React from 'react';
import Navigator from './Navbar';
import Footer from './Footer';
import {Grid,Row,Col} from 'react-bootstrap';
import { browserHistory } from 'react-router';

export default class Layout extends React.Component{
    constructor(props) {
        super(props);
    }

    getPathAfterReload(){
        $.ajax({
            url: '/getPathAfterReload',
            type: 'GET',
            success: (function(data) {
                console.log(data.content);
                browserHistory.push(data.content);
            }).bind(this),
            error: (function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.status+":"+xhr.responseText)
            }).bind(this)
        });

    }

    render(){
        return (
            <div>
                <Navigator/>
                <Grid>
                    <Row>
                        <Col sm={0} md={1}  lg={1}/>
                        <Col sm={12} md={10} lg={10} className="main-block">
                            {this.props.children}
                        </Col>
                        <Col sm={0} md={1} lg={1}/>
                    </Row>
                </Grid>
                <Footer/>
            </div>
        );
    }
};

import React from 'react';
import {Grid,Row,Col,Image,Button} from 'react-bootstrap';

export default class Error extends React.Component{

    render(){
        return (
        <Row className="mem">
            <Col sm={0} md={1} />
            <Col sm={12} md={10} >
                <h2>Status: {this.props.status}</h2>
                <hr />

                    <h4>Message: {this.props.mess}</h4>

            </Col>
            <Col sm={0} md={1} />
        </Row>
        );
    }
}


import React from 'react';
import ScroolMenu from './ScroolMenu';
import {Grid,Row,Col,Button} from 'react-bootstrap';

export default class DownPanel extends React.Component{

    render(){
        return (
            <Row className="downPanel">
                <Col sm={0} md={1} />
                <Col sm={12} md={10} >
                    <div className="buttonTollbar">
                        <Button bsStyle="primary" className="myButtonStyle myButtonStyle1">Next page!</Button><Button bsStyle="primary" className="myButtonStyle myButtonStyle2"></Button>
                    <ScroolMenu/>
                    </div>
                </Col>
                <Col sm={0} md={1} />
            </Row>
        );
    }
};

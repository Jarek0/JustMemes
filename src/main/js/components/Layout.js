import React from 'react';
import Navigator from './Navbar';
import Footer from './Footer';
import {Grid,Row,Col} from 'react-bootstrap';

export default class Layout extends React.Component{
    constructor(props) {
        super(props);
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

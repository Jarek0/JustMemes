import React from 'react';
import Navigator from './Navbar';
import Mem from './Mem';
import DownPanel from './DownPanel';
import Footer from './Footer';
import {Grid,Row,Col} from 'react-bootstrap';

export default class Layout extends React.Component{
    constructor(props) {
        super(props);
        this.state = {memes: []};
    }

    componentDidMount() {
        $.ajax({
            url: '/mem/page/'+0,
            type: 'GET',
            cache: false,
            success: function(data) {
                this.setState({ memes: data });
            }.bind(this),
            error: function() {
                console.log('error');
            }.bind(this)
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
                                {
                                    this.state.memes.map((mem) =>
                                        <Mem title={mem.title} key={mem.id} id={mem.id} fileType={mem.fileType} />
                                    )
                                }
                            <DownPanel />
                        </Col>
                        <Col sm={0} md={1} lg={1}/>
                    </Row>
                </Grid>
                <Footer/>
            </div>
        );
    }
};
